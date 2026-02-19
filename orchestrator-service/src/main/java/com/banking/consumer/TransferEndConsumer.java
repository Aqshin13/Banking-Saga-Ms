package com.banking.consumer;

import com.banking.service.impl.ManageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class TransferEndConsumer {

    private final ManageServiceImpl service;

    @KafkaListener(topics = "top-up-end-topic", groupId = "orchestrator-service-group")
    public void consume(Long id) {
        try {
            service.handleTransferEnd(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
