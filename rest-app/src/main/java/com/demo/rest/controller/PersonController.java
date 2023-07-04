package com.demo.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.rest.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class PersonController {
    private Map<String, Person> personMap = 
    		Map.of(
    		"Anbu", new Person("Anbu", "Sydeny"),
    		"Sampath", new Person("Sampath", "Melbourne")
    		);
    

    @GetMapping(value = "/person/{name}")
    public Person personByName(@PathVariable String name) {
        return personMap.get(name);
    }

    @GetMapping(value = "/person")
    public List<Person> personList() {
        return new ArrayList<>(personMap.values());
    }
}
