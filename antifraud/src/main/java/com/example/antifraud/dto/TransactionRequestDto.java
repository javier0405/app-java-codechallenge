package com.example.antifraud.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TransactionRequestDto {
    private UUID accountExternalIdDebit;
    private UUID accountExternalIdCredit;
    private Integer transferTypeId;
    private Double value;
}
