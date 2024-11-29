package com.example.antifraud.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TransactionResponseDto {
    private UUID transactionExternalId;
    private String transactionStatus;
    private Double value;
    private LocalDateTime createdAt;
}
