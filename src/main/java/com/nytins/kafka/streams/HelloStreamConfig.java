package com.nytins.kafka.streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloStreamConfig {

    @Bean
    public KStream<String, String> helloStream(StreamsBuilder streamsBuilder) {
        KStream<String, String> stream = streamsBuilder
                .stream("input-topic", Consumed.with(Serdes.String(), Serdes.String()));
        stream.map(this::uppercaseValue)
                .to("output-topic", Produced.with(Serdes.String(), Serdes.String()));
        return stream;
    }

    private KeyValue<String, String> uppercaseValue(String key, String value) {
        return new KeyValue<>(key, value.toUpperCase());
    }

}
