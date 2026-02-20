package com.banking.consumer;


import com.banking.dto.event.Event;
import com.banking.service.impl.ManageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateRefundConsumer {


    private final ManageServiceImpl service;


    @KafkaListener(topics = "create-refund-topic", groupId = "orchestrator-service-group")
    public void consume(Event event) {
        try {
            service.handleRefundCreate(event);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
