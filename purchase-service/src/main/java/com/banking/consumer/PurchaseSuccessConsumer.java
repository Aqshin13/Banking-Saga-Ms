package com.banking.consumer;

import com.banking.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PurchaseSuccessConsumer {


    private final TransactionService service;

    @KafkaListener(topics = "purchase-success-topic", groupId = "purchase-group")
    public void consume(Long id) {
        try {
            service.onSUCCESS(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





}
