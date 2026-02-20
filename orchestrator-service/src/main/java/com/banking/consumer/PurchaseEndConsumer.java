package com.banking.consumer;

import com.banking.service.impl.ManageServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.BackOff;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class PurchaseEndConsumer {

    private final ManageServiceImpl service;

    @RetryableTopic(
            attempts = "4",
            backOff = @BackOff(delay = 2000, multiplier = 1.5, maxDelay = 10000),
            autoCreateTopics = "true",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE
    )
    @KafkaListener(topics = "purchase-end-topic", groupId = "orchestrator-service-group")
    public void consume(Long id) {
        try {
            service.handlePurchaseEnd(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DltHandler
    public void handleDlt(Long id) {
        log.error("Purchase end topic error {}",id);
    }
}
