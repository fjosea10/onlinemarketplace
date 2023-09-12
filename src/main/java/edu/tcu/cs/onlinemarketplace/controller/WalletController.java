package edu.tcu.cs.onlinemarketplace.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.tcu.cs.onlinemarketplace.domain.Result;
import edu.tcu.cs.onlinemarketplace.domain.StatusCode;
import edu.tcu.cs.onlinemarketplace.domain.Wallet;
import edu.tcu.cs.onlinemarketplace.service.WalletService;

@RestController
public class WalletController {

    private WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/wallet")
    public Result viewAll() {
        List<Wallet> all = walletService.viewAll();
        Result result = new Result(true, StatusCode.SUCCESS, "View all success", all);
        return result;
    }

    @GetMapping("/wallet/{walletId}") 
    public Result viewBalance(@PathVariable String walletId) {
        Wallet wallet = walletService.findByID(walletId);
        return new Result(true, StatusCode.SUCCESS, "Found balance success", wallet.getCreditBalance());
    }
}
