package com.example.antifraud.kafka;

import com.example.antifraud.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private static final String TOPIC = "transactions";

    @Autowired
    private KafkaTemplate<String, Transaction> kafkaTemplate;

    public void sendMessage(Transaction transaction) {
        kafkaTemplate.send(TOPIC, transaction);
    }
}


