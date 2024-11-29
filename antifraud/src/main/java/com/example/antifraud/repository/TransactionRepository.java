package com.example.antifraud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.antifraud.entity.Transaction;

import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByTransactionExternalId(UUID transactionExternalId);
}
