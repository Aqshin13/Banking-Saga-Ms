package com.banking.service.impl;


import com.banking.dto.event.Event;
import com.banking.service.inter.ManageServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManageServiceImpl implements ManageServiceInter {


    private final KafkaTemplate<String, Event> kafkaTemplate;
    private final KafkaTemplate<String, Long> producer;


    public void handleTopUpCreate(Event event) {
        kafkaTemplate.send("transfer-start-topic", event);
    }


    public void handleTransferEnd(Long id) {
        producer.send("top-up-success-topic", id);

    }

    public void handleTransferCancel(Long id) {
        producer.send("top-up-cancel-topic", id);

    }

    public void handlePurchaseCreate(Event event) {
        kafkaTemplate.send("purchase-start-topic", event);
    }

    public void handlePurchaseEnd(Long event) {
        producer.send("purchase-success-topic", event);
    }

    public void handlePurchaseFailed(Long id) {
        producer.send("purchase-cancel-topic", id);
    }
}
