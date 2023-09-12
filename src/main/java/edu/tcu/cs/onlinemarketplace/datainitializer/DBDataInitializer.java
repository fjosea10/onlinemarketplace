package edu.tcu.cs.onlinemarketplace.datainitializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import edu.tcu.cs.onlinemarketplace.dao.WalletDao;
import edu.tcu.cs.onlinemarketplace.domain.Wallet;

@Component
public class DBDataInitializer implements CommandLineRunner{

    private WalletDao walletDao;  

    public DBDataInitializer(WalletDao walletDao) {
        this.walletDao = walletDao;
    }

    @Override
    public void run(String... args) throws Exception {

        Wallet w1 = new Wallet();
        w1.setId("1001");
        w1.setCreditBalance(50);
        w1.setCreationTime("2022-10-03");
        w1.setLastModified("2023-05-03");
        w1.setStatus(true);

        Wallet w2 = new Wallet();
        w2.setId("1002");
        w2.setCreditBalance(40);
        w2.setCreationTime("2022-09-03");
        w2.setLastModified("2023-01-13");
        w2.setStatus(true);

        Wallet w3 = new Wallet();
        w3.setId("1003");
        w3.setCreditBalance(20);
        w3.setCreationTime("2019-09-03");
        w3.setLastModified("2020-01-13");
        w3.setStatus(true);

        Wallet w4 = new Wallet();
        w4.setId("1004");
        w4.setCreditBalance(430);
        w4.setCreationTime("2019-09-03");
        w4.setLastModified("2020-01-13");
        w4.setStatus(true);

        Wallet w5 = new Wallet();
        w5.setId("1005");
        w5.setCreditBalance(54);
        w5.setCreationTime("2018-11-03");
        w5.setLastModified("2013-01-13");
        w5.setStatus(true);

        walletDao.save(w1);
        walletDao.save(w2);
        walletDao.save(w3);
        walletDao.save(w4);
        walletDao.save(w5);
    }
}
