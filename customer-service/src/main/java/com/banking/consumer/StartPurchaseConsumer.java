package com.banking.consumer;

import com.banking.dto.event.Event;
import com.banking.service.inter.CustomerServiceInter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.BackOff;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class StartPurchaseConsumer {


    private final CustomerServiceInter customerServiceInter;

    @RetryableTopic(
            attempts = "4",
            backOff = @BackOff(delay = 2000, multiplier = 1.5, maxDelay = 10000),
            autoCreateTopics = "true",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE
    )
    @KafkaListener(topics = "purchase-start-topic",
            groupId = "customer-service-group")
    public void consume(Event event) {
            customerServiceInter.processPurchase(event);
    }



    @DltHandler
    public void handleDlt(Event event) {
      log.error("Purchase start topic error {}",event);
    }


}
