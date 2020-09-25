package com.assignment.storage.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageAppConfig {

    @Value("${storage.service.amqp.queue.xml}")
    private String queue;

    @Bean
    public Queue getQueue(){
        return new Queue(queue);
    }
}
