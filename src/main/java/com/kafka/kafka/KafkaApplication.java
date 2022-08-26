package com.kafka.kafka;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, User> kafkaTemplate) {
        return args -> {
            for (int i = 0; i < 100000; i++) {
                kafkaTemplate.send("testTopic", new User("Miko", "WWW", i));
            }
        };
    }
}
