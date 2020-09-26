package com.assignment.storage.service;

import com.assignment.storage.model.Person;
import com.assignment.storage.protobuf.PersonProtos;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmqpService {

    @Autowired
    StorageService storageService;

    @RabbitListener(queues = "${storage.service.amqp.queue.xml}")
    public void receiveForXml(byte[] encryptedBytes){
        try {
            PersonProtos.Person protoPerson = PersonProtos.Person.parseFrom(encryptedBytes);
            Person person = new Person(protoPerson);
            System.out.println("XML person received: " + protoPerson.toString());
            storageService.storeAsXml(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "${storage.service.amqp.queue.csv}")
    public void receiveForCsv(byte[] encryptedBytes){
        try {
            PersonProtos.Person protoPerson = PersonProtos.Person.parseFrom(encryptedBytes);
            Person person = new Person(protoPerson);
            System.out.println("CSV person received: " + protoPerson.toString());
            storageService.storeAsCsv(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
