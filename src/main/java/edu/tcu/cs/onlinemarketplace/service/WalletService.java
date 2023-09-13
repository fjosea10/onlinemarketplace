package edu.tcu.cs.onlinemarketplace.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.tcu.cs.onlinemarketplace.dao.WalletDao;
import edu.tcu.cs.onlinemarketplace.domain.Wallet;

@Service
@Transactional
public class WalletService {
    private WalletDao walletDao;
    
    public WalletService(WalletDao walletDao) {
        this.walletDao = walletDao;
    } 

    // list all wallets
    public List<Wallet> viewAll() {
        return walletDao.findAll();
    }

    // find wallet by id
    public Wallet findById(String walletId) {
        return walletDao.findById(walletId).get();
    }

    /*
     * the user can buy credits, adding them to their wallet balance.
     * if account is frozen, cannot modify
     * set the last modified date when balance is modified.
     */
    public Boolean addCredits(Wallet wallet, Integer amt) {
        // if account status is true, able to add credits: return true
        if(wallet.getStatus()) {
            wallet.setCreditBalance(wallet.getCreditBalance() + amt);
            wallet.setLastModified(getDate());
            walletDao.save(wallet);
            return true;
        }
        else {
            return false;
        }
    }

    /* the user can remove/withdraw credits, adding them to their wallet balance.
     * if account is frozen, cannot modify
     * set the last modified date when balance is modified.
     * cannot remove credits if amount to remove is greater than current balance
     */
    public Boolean removeCredits(Wallet wallet, Integer amt) {
        // return false if amt is greater than balance or if account is frozen
        if (wallet.getCreditBalance() < amt || wallet.getStatus() == false) return false; 
        wallet.setCreditBalance(wallet.getCreditBalance() - amt);
        wallet.setLastModified(getDate());
        walletDao.save(wallet);
        return true; // return true if balance was reduced
    }

    /*
     * transfer credits between wallets.
     */
    public Boolean pay(Wallet sourceWallet, Wallet targetWallet, int amt) { 
        // if credits are removed from source wallet, add credits to target wallet
        if (removeCredits(sourceWallet, amt)) {
            // if credits are not added (addCredits returned false)
            if (!addCredits(targetWallet, amt))
            { 
                addCredits(sourceWallet, amt); //add removed credits back to source wallet
                return false;
            }
            return true;
        }
        else {
            return false;
        }
    }

    // get the current date and convert to string
    public String getDate() {
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        return dateFormat.format(date);  
    }

    //  change wallet status from frozen to normal or viceversa
    public void changeStatus(Wallet wallet) {
        wallet.setStatus(!wallet.getStatus());
        walletDao.save(wallet);
    }
}
