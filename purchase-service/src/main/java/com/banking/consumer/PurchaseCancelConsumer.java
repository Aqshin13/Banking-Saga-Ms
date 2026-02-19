package com.banking.consumer;


import com.banking.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PurchaseCancelConsumer {

private final TransactionService service;


    @KafkaListener(topics = "purchase-cancel-topic", groupId = "purchase-group")
    public void consume(Long id) {
        try {
            System.out.println(id+"      +++++++++++++++++++++++++++++");

            service.onFAILED(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
