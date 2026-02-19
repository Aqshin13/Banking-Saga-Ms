package com.banking.consumer;

import com.banking.service.impl.TransferServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TopUpSuccessConsumer {


    private  final TransferServiceImpl service;

    @KafkaListener(topics = "top-up-success-topic", groupId = "top-up-group")
    public void consume(Long id) {
        try {
            service.onSUCCESS(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
