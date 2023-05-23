package com.nytins.kafka.streams;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@EnableKafkaStreams
public class HelloStreamTopicsConfig {

    @Bean
    public NewTopic inputTopic() {
        return TopicBuilder.name("input-topic")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic outputTopic() {
        return TopicBuilder.name("output-topic")
                .partitions(1)
                .replicas(1)
                .build();
    }

}
