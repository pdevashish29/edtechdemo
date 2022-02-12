package com.pdp.reactors.controller;

import com.pdp.reactors.model.Person;
import com.pdp.reactors.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("v1/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("hello")
    public String getHello(){
        System.out.println("Welcome");
        return  new Date().toLocaleString() ;
    }



    @GetMapping
    public List<Person> getPersons(){
        return personService.findAllPersons();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable  String id) {
        return personService.findPersonById(id);
    }

    @PostMapping("/")
    public Person savePerson(@RequestBody Person person){
        return personService.savePerson(person);
    }



}
