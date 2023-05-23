package com.nytins.kafka.streams;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EmbeddedKafka(
        partitions = 1,
        topics = {
                "input-topic", "output-topic"
        },
        bootstrapServersProperty = "spring.kafka.bootstrap-servers"
)
class HelloStreamTests {

    @Autowired
    private EmbeddedKafkaBroker embeddedKafka;

    @Test
    void capitalizesInput() {
        publishMessage("input-topic", "hello");
        assertThat(readMessage("output-topic")).isEqualTo("HELLO");
    }

    private void publishMessage(String topic, String message) {
        Map<String, Object> producerProps = KafkaTestUtils.producerProps(embeddedKafka);
        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(producerProps);
        Producer<String, String> producer = producerFactory.createProducer();
        producer.send(new ProducerRecord<>(topic, message));
    }

    private String readMessage(String topic) {
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("testGroup", "true", embeddedKafka);
        ConsumerFactory<String, String> cf = new DefaultKafkaConsumerFactory<>(consumerProps);
        Consumer<String, String> consumer = cf.createConsumer();
        embeddedKafka.consumeFromAnEmbeddedTopic(consumer, topic);
        ConsumerRecords<String, String> replies = KafkaTestUtils.getRecords(consumer, 10000);
        return replies.records(topic).iterator().next().value();
    }

}
