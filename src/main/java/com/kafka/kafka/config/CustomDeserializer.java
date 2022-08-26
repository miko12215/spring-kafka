package com.kafka.kafka.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.kafka.User;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class CustomDeserializer implements Deserializer<User> {

    @Override
    public User deserialize(String s, byte[] bytes) {
        try {
            return new ObjectMapper().readValue(new String(bytes, StandardCharsets.UTF_8), User.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public User deserialize(String topic, Headers headers, byte[] data) {
        try {
            return new ObjectMapper().readValue(new String(data, StandardCharsets.UTF_8), User.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
