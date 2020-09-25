package com.assignment.storage.service;

import com.assignment.storage.protobuf.PersonProtos;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AmqpService {

    @RabbitListener(queues = "${storage.service.amqp.queue.xml}")
    public void receiveForXml(byte[] encryptedBytes){
        try {
            PersonProtos.Person person = PersonProtos.Person.parseFrom(encryptedBytes);
            System.out.println("XML person received: " + person.toString());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "${storage.service.amqp.queue.csv}")
    public void receiveForCsv(byte[] encryptedBytes){
        try {
            PersonProtos.Person person = PersonProtos.Person.parseFrom(encryptedBytes);
            System.out.println("CSV person received: " + person.toString());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}
