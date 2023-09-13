package edu.tcu.cs.onlinemarketplace.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tcu.cs.onlinemarketplace.domain.Result;
import edu.tcu.cs.onlinemarketplace.domain.StatusCode;
import edu.tcu.cs.onlinemarketplace.domain.Wallet;
import edu.tcu.cs.onlinemarketplace.service.WalletService;
import jakarta.transaction.Transactional;

@Transactional
@RestController
public class WalletController {

    private WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    // view all wallets and their attributes
    @GetMapping("/wallet")
    public Result viewAll() {
        List<Wallet> all = walletService.viewAll();
        Result result = new Result(true, StatusCode.SUCCESS, "View all success", all);
        return result;
    }

    // User can view their balance by using this URL and specifying 
    // wallet ID as a path variable
    @GetMapping("/wallet/{walletId}") 
    public Result viewBalance(@PathVariable String walletId) {
        Wallet wallet = walletService.findById(walletId);
        return new Result(true, StatusCode.SUCCESS, "Found balance success", wallet.getCreditBalance());
    }

    // user can buy credits by specifying walletId and the amount of credits
    // to buy as path variables. Number of credits will be increased.
    @PutMapping("/wallet/{walletId}/{amt}")
    public Result buyCredits(@PathVariable String walletId, @PathVariable String amt) {
        Wallet wallet = walletService.findById(walletId);
        if (!wallet.getStatus()) return new Result(false, StatusCode.FAILURE, "Wallet is frozen");
        else {
            walletService.addCredits(wallet, Integer.parseInt(amt));
            return new Result(true, StatusCode.SUCCESS, "Buy credit Success");
        }
    }

    /** user can transfer credits (pay other users).
     * When user A makes payment to user B, the system shall decrease the balance of user A
     * and then increase the balance of user B. Note, the two operations must be done within
     * the same transaction
     */
    @PutMapping("transfer/{sourceId}/{targetId}/{amt}")
    public Result pay(@PathVariable String sourceId, @PathVariable String targetId, @PathVariable String amt) {
        Wallet sourceWallet = walletService.findById(sourceId);
        Wallet targetWallet = walletService.findById(targetId);
        if (walletService.pay(sourceWallet, targetWallet, Integer.parseInt(amt))){
            return new Result(true, StatusCode.SUCCESS, "Transfer Credit Success");
        }
        else {
            return new Result(false, StatusCode.FAILURE, "No sufficient funds or account frozen");
        }
    }
    /** 
     * user can withdraw credits from their wallet back
     * to their bank account.
     */
    @PutMapping("/withdraw/{walletId}/{amt}")
    public Result withdraw(@PathVariable String walletId, @PathVariable String amt) {
        Wallet wallet = walletService.findById(walletId);
        if(!walletService.removeCredits(wallet, Integer.parseInt(amt))) {
            return new Result(true, StatusCode.SUCCESS, "not enough funds");
        }
        // implement transfer credits to bank account
        return new Result(true, StatusCode.SUCCESS, "Remove credit Success");
   
    }

    /*
     * user can change the status.
     * if frozen, account will unfreeze.
     * if normal, account will freeze.
     */
    @PutMapping("/changeStatus/{walletId}")
    public Result changeStatus(@PathVariable String walletId) {
        Wallet wallet = walletService.findById(walletId);
        walletService.changeStatus(wallet);
        return new Result(true, StatusCode.SUCCESS, "changed status successfully");
    }

    
}
