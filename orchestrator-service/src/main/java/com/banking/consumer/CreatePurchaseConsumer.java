package com.banking.consumer;

import com.banking.dto.event.Event;
import com.banking.service.impl.ManageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreatePurchaseConsumer {

    private final ManageServiceImpl service;

    @KafkaListener(topics = "create-purchase-topic", groupId = "orchestrator-service-group")
    public void consume(Event event) {
        try {
            service.handlePurchaseCreate(event);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
