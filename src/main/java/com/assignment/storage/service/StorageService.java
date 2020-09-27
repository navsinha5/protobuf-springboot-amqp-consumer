package com.assignment.storage.service;

import com.assignment.storage.model.Person;
import com.assignment.storage.model.StorageException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class StorageService {

    @Value("${storage.service.file.base-path}")
    private String basePath;

    public void storeAsXml(Person person) throws IOException {
        try {
            try {
                Person oldPerson = readData(person.getId());
                if (oldPerson != null) {
                    person = mergePerson(oldPerson, person);
                }
            }catch (StorageException ex){
                System.out.println("no previous data found: " + ex.getMessage() );
            }

            File csvFile = new File(person.getId() + ".csv");
            if(csvFile.exists()){
                csvFile.delete();
            }

            new XmlMapper().writeValue(new File(person.getId() + ".xml"), person);
        } catch (IOException e) {
            System.out.println("error while storing data in xml: " + e.getMessage());
            throw e;
        }
    }

    public void storeAsCsv(Person person) throws IOException {
        try{
            try {
                Person oldPerson = readData(person.getId());
                if (oldPerson != null) {
                    person = mergePerson(oldPerson, person);
                }
            }catch (StorageException ex) {
                System.out.println("no previous data found: " + ex.getMessage());
            }

            File xmlFile = new File(person.getId() + ".xml");
            if(xmlFile.exists()){
                xmlFile.delete();
            }

            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = mapper.schemaFor(Person.class)
                                .withUseHeader(true);

            mapper.writer(schema)
                    .writeValue(new File(person.getId() + ".csv"), person);
        } catch (IOException e) {
            System.out.println("error while saving csv file: " + e.getMessage());
            throw e;
        }
    }

    public Person readFromXml(String id) throws IOException {
        File xmlFile = new File(id + ".xml");
        if(!xmlFile.exists()){
            return null;
        }
        return new XmlMapper().readValue(xmlFile, Person.class);
    }

    public Person readFromCsv(String id) throws IOException {
        File csvFile = new File(id + ".csv");
        if(!csvFile.exists()){
            return null;
        }
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(Person.class)
                .withUseHeader(true);
        return mapper.readerFor(Person.class)
                .with(schema)
                .readValue(new File(id + ".csv"));
    }

    public Person readData(String id) throws IOException {
        Person person = (person = readFromCsv(id)) != null ? person : readFromXml(id);
        if(person == null){
            throw new StorageException(404, "file not found");
        }
        return person;
    }

    public Person mergePerson(Person op, Person np){
        op.setName(np.getName().isEmpty() ? op.getName() : np.getName());
        op.setDob(np.getDob().isEmpty() ? op.getDob() : np.getDob() );
        op.setSalary(np.getSalary().isEmpty() ? op.getSalary() : np.getSalary());
        op.setAge(np.getAge() <= 0 ? op.getAge() : np.getAge());
        return op;
    }

}
