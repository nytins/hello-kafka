package com.nytins.kafka.regular;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class HelloConsumer {

    @KafkaListener(topics = "hello-topic")
    public void processEvent(HelloEvent helloEvent) {
        System.out.println("helloEvent = " + helloEvent);
    }

}
