package com.banking.consumer;

import com.banking.dto.event.Event;
import com.banking.service.impl.ManageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RefundEndConsumer {


    private final ManageServiceImpl service;


    @KafkaListener(topics = "refund-end-topic", groupId = "orchestrator-service-group")
    public void consume(Long event) {
        try {
            service.handleRefundSuccess(event);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
