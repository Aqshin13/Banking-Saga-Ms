package com.banking.consumer;

import com.banking.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefundSuccessConsumer {

    private final TransactionService service;


    @KafkaListener(topics = "refund-success-topic", groupId = "purchase-group")
    public void consume(Long id) {
        try {
            service.onSUCCESSRefund(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
