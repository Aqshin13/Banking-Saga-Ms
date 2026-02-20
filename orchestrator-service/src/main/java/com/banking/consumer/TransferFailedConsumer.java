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


@RequiredArgsConstructor
@Component
@Slf4j
public class TransferFailedConsumer {

    private final ManageServiceImpl service;

    @RetryableTopic(
            attempts = "4",
            backOff = @BackOff(delay = 2000, multiplier = 1.5, maxDelay = 10000),
            autoCreateTopics = "true",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE
    )
    @KafkaListener(topics = "top-up-failed-topic", groupId = "orchestrator-service-group")
    public void consume(Long id) {
            service.handleTransferCancel(id);
    }

    @DltHandler
    public void handleDlt(Long id) {
        log.error("Top-up failed topic error {}",id);
    }
}
