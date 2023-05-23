package com.nytins.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class HelloProducer implements CommandLineRunner {

    private KafkaTemplate<String, HelloEvent> kafkaTemplate;

    @Autowired
    public HelloProducer(KafkaTemplate<String, HelloEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        HelloEvent event = new HelloEvent();
        event.setName(LocalDateTime.now().toString());
        kafkaTemplate.send("hello-topic", event);
    }

}
