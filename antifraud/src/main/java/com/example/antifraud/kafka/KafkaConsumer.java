package com.example.antifraud.kafka;

import com.example.antifraud.entity.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "transaction-status", groupId = "antifraud")
    public void consume(Transaction transaction) {
        // Procesar actualización de estado
    }
}
