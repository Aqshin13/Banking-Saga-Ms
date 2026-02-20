package com.banking.consumer;


import com.banking.service.impl.TransferServiceImpl;
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
public class TopUpCancelConsumer {

    private  final TransferServiceImpl service;

    @RetryableTopic(
            attempts = "4",
            backOff = @BackOff(delay = 2000, multiplier = 1.5, maxDelay = 10000),
            autoCreateTopics = "true",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE
    )
    @KafkaListener(topics = "top-up-cancel-topic", groupId = "top-up-group")
    public void consume(Long id) {
            service.onFAILED(id);
    }

    @DltHandler
    public void handleDlt(Long id) {
        log.error("Top-up cancel topic error {}",id);
    }
}
