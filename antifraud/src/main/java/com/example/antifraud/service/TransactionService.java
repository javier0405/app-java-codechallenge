package com.example.antifraud.service;

import com.example.antifraud.dto.TransactionRequestDto;
import com.example.antifraud.dto.TransactionResponseDto;
import com.example.antifraud.kafka.KafkaProducer;
import com.example.antifraud.repository.TransactionRepository;
import com.example.antifraud.entity.Transaction;
import com.example.antifraud.exception.TransactionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final KafkaProducer kafkaProducer;

    public TransactionResponseDto createTransaction(TransactionRequestDto request) {
        Transaction transaction = new Transaction();
        transaction.setTransactionExternalId(UUID.randomUUID());
        transaction.setAccountExternalIdDebit(request.getAccountExternalIdDebit());
        transaction.setAccountExternalIdCredit(request.getAccountExternalIdCredit());
        transaction.setTransferTypeId(request.getTransferTypeId());
        transaction.setValue(request.getValue());
        transaction.setTransactionStatus("pending");

        if (request.getValue() > 1000) {
            transaction.setTransactionStatus("rejected");
        }

        transaction = transactionRepository.save(transaction);
        kafkaProducer.sendMessage(transaction);

        // Retornar la transacción sin guardarla en la caché
        return mapToResponseDto(transaction);
    }

    public TransactionResponseDto getTransaction(UUID transactionExternalId) {
        Transaction transaction = transactionRepository.findByTransactionExternalId(transactionExternalId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));

        return mapToResponseDto(transaction);
    }

    private TransactionResponseDto mapToResponseDto(Transaction transaction) {
        TransactionResponseDto response = new TransactionResponseDto();
        response.setTransactionExternalId(transaction.getTransactionExternalId());
        response.setTransactionStatus(transaction.getTransactionStatus());
        response.setValue(transaction.getValue());
        response.setCreatedAt(transaction.getCreatedAt());
        return response;
    }
}