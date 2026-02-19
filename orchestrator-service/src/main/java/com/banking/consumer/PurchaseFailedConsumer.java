package com.banking.consumer;

import com.banking.service.impl.ManageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PurchaseFailedConsumer {


    private final ManageServiceImpl service;


    @KafkaListener(topics = "purchase-failed-topic", groupId = "orchestrator-service-group")
    public void consume(Long id) {
        try {
            service.handlePurchaseFailed(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
