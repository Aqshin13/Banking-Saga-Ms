package com.banking.consumer;


import com.banking.dto.event.Event;
import com.banking.service.inter.CustomerServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartTransferConsumer {

    private final CustomerServiceInter customerServiceInter;

    @KafkaListener(topics = "transfer-start-topic",
            groupId = "customer-service-group")
    public void consume(Event event) {
        try {
            customerServiceInter.processTransfer(event);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
