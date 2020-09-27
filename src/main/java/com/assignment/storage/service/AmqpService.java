package com.assignment.storage.service;

import com.assignment.storage.model.Person;
import com.assignment.storage.protobuf.PersonProtos;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

@Service
public class AmqpService {

    @Autowired
    StorageService storageService;

    @Value("${storage.service.encryption.key}")
    String key;

    @RabbitListener(queues = "${storage.service.amqp.queue.xml}")
    public void receiveForXml(byte[] encryptedBytes) throws Exception {
        byte[] decryptedBytes = decrypt(encryptedBytes);
        PersonProtos.Person protoPerson = PersonProtos.Person.parseFrom(decryptedBytes);
        Person person = new Person(protoPerson);
        System.out.println("XML person received: " + protoPerson.toString());
        storageService.storeAsXml(person);
    }

    @RabbitListener(queues = "${storage.service.amqp.queue.csv}")
    public void receiveForCsv(byte[] encryptedBytes) throws Exception {
        byte[] decryptedBytes = decrypt(encryptedBytes);
        PersonProtos.Person protoPerson = PersonProtos.Person.parseFrom(decryptedBytes);
        Person person = new Person(protoPerson);
        System.out.println("CSV person received: " + protoPerson.toString());
        storageService.storeAsCsv(person);
    }

    private byte[] decrypt(byte[] encryptedData) throws Exception {
        byte[] keyBytes = DatatypeConverter.parseBase64Binary(key);
        Key finalKey = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, finalKey);
        return cipher.doFinal(encryptedData);
    }
}
