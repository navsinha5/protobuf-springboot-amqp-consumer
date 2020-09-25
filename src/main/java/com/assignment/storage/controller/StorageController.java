package com.assignment.storage.controller;

import com.assignment.storage.model.Person;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@RequestMapping("/storage")
public class StorageController {

    @GetMapping("/person")
    public Person getPerson(@RequestParam("id") String id){
        return null;
    }
}
