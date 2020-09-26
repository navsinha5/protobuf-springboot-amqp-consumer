package com.assignment.storage.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageAppConfig {

    @Value("${storage.service.amqp.queue.xml}")
    private String xmlQueue;

    @Value("${storage.service.amqp.queue.csv}")
    private String csvQueue;

    @Bean
    public Queue getXmlQueue(){
        return new Queue(xmlQueue);
    }

    @Bean
    public Queue getCsvQueue(){
        return new Queue(csvQueue);
    }
}
