package com.kafka.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerComponent {

    @Autowired
    ObjectMapper mapper;

    @KafkaListener(topics = "testTopic",
                   groupId = "second",
                   containerFactory = "factory ")
    void listener(User user) {
        System.out.println(user.getAge() + user.getFirstname());

    }

}
