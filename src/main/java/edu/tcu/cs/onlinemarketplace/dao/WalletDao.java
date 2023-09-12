package edu.tcu.cs.onlinemarketplace.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.tcu.cs.onlinemarketplace.domain.Wallet;

public interface WalletDao extends JpaRepository<Wallet, String> {
    
}
