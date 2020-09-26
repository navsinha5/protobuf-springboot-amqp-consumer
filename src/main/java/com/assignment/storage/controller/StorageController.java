package com.assignment.storage.controller;

import com.assignment.storage.model.Person;
import com.assignment.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    StorageService storageService;

    @GetMapping("/person")
    public Person getPerson(@RequestParam("name") String name) throws Exception {
        return storageService.readData(name);
    }
}
