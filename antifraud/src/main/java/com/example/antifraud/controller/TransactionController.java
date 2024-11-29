package com.example.antifraud.controller;

import com.example.antifraud.dto.*;
import com.example.antifraud.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    @Autowired
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDto> createTransaction(@RequestBody TransactionRequestDto request) {
        return ResponseEntity.ok(transactionService.createTransaction(request));
    }

    @GetMapping("/{transactionExternalId}")
    public ResponseEntity<TransactionResponseDto> getTransaction(@PathVariable UUID transactionExternalId) {
        return ResponseEntity.ok(transactionService.getTransaction(transactionExternalId));
    }
}
