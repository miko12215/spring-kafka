package com.kafka.kafka.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.kafka.User;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class CustomSerializer implements Serializer<User> {

    @Override
    public void close() {
        Serializer.super.close();
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, User user) {
            try {
                if (user == null){
                    return new byte[0];
                }
                return objectMapper.writeValueAsBytes(user);
            } catch (Exception e) {
                throw new SerializationException("Error when serializing MessageDto to byte[]");
            }
    }

    @Override
    public byte[] serialize(String topic, Headers headers, User data) {
        return Serializer.super.serialize(topic, headers, data);
    }
}
