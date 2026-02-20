package com.banking.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {


    @Bean
    public NewTopic createTopUpTopic(){
        return TopicBuilder.name("create-transfer-topic")
                .build();
    }

    @Bean
    public NewTopic failedTopUpTopic(){
        return TopicBuilder.name("top-up-failed-topicc")
                .build();
    }

    @Bean
    public NewTopic successTopUpTopic(){
        return TopicBuilder.name("top-up-success-topic")
                .build();
    }

    @Bean
    public NewTopic endTopUpTopic(){
        return TopicBuilder.name("top-up-end-topic")
                .build();
    }

    @Bean
    public NewTopic cancelTopUpTopic(){
        return TopicBuilder.name("top-up-cancel-topic")
                .build();
    }

    @Bean
    public NewTopic startPurchaseTopic(){
        return TopicBuilder.name("purchase-start-topic")
                .build();
    }

    @Bean
    public NewTopic createPurchaseTopic(){
        return TopicBuilder.name("create-purchase-topic")
                .build();
    }

    @Bean
    public NewTopic purchaseEndTopic(){
        return TopicBuilder.name("purchase-end-topic")
                .build();
    }

    @Bean
    public NewTopic purchaseFailedTopic(){
        return TopicBuilder.name("purchase-failed-topic")
                .build();
    }


    @Bean
    public NewTopic purchaseSuccessTopic(){
        return TopicBuilder.name("purchase-success-topic")
                .build();
    }

    @Bean
    public NewTopic purchaseCancelTopic(){
        return TopicBuilder.name("purchase-cancel-topic")
                .build();
    }


    @Bean
    public NewTopic createRefundTopic(){
        return TopicBuilder.name("create-refund-topic").build();
    }


    @Bean
    public NewTopic endRefundTopic(){
        return TopicBuilder.name("refund-end-topic").build();
    }


    @Bean
    public NewTopic failedRefundTopic(){
        return TopicBuilder.name("refund-failed-topic").build();
    }


    @Bean
    public NewTopic successRefundTopic(){
        return TopicBuilder.name("refund-success-topic").build();
    }


    @Bean
    public NewTopic cancelRefundTopic(){
        return TopicBuilder.name("refund-cancel-topic").build();
    }
}
