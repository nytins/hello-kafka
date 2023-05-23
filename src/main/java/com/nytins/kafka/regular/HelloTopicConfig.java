package com.nytins.kafka.regular;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class HelloTopicConfig {

    @Bean
    public NewTopic helloTopic() {
        return TopicBuilder
                .name("hello-topic")
                .partitions(1)
                .replicas(1)
                .build();
    }

}
