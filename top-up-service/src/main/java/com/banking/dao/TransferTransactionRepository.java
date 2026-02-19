package com.banking.dao;

import com.banking.entity.TransferTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferTransactionRepository
        extends JpaRepository<TransferTransaction,Long> {
}
