package com.nytins.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;

public class HelloDeserializer implements Deserializer<HelloEvent> {

    private static final ObjectMapper mapper = JsonMapper.builder()
            .findAndAddModules()
            .build();

    @Override
    public HelloEvent deserialize(String topic, byte[] data) {
        try {
            return mapper.readValue(data, HelloEvent.class);
        } catch (IOException e) {
            throw new SerializationException(e);
        }

    }
}
