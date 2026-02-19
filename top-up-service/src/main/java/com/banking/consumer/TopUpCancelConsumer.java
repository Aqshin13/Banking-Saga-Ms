package com.banking.consumer;


import com.banking.service.impl.TransferServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TopUpCancelConsumer {

    private  final TransferServiceImpl service;

    @KafkaListener(topics = "top-up-cancel-topic", groupId = "top-up-group")
    public void consume(Long id) {
        try {
            service.onFAILED(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
