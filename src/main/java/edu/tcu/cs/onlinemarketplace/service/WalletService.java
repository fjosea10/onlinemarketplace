package edu.tcu.cs.onlinemarketplace.service;

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

    public List<Wallet> viewAll() {
        return walletDao.findAll();
    }

    public Wallet findByID(String walletId) {
        return walletDao.findById(walletId).get();
    }
}
