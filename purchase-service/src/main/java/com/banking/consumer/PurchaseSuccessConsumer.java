package com.banking.consumer;

import com.banking.dto.event.Event;
import com.banking.service.TransactionService;
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
public class PurchaseSuccessConsumer {


    private final TransactionService service;

    @RetryableTopic(
            attempts = "4",
            backOff = @BackOff(delay = 2000, multiplier = 1.5, maxDelay = 10000),
            autoCreateTopics = "true",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE
    )
    @KafkaListener(topics = "purchase-success-topic", groupId = "purchase-group")
    public void consume(Long id) {
            service.onSUCCESS(id);
    }

    @DltHandler
    public void handleDlt(Long id) {
        log.error("Purchase success topic error {}",id);
    }





}
