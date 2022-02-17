package com.pdp.reactors.service;


import com.pdp.reactors.controller.repo.PersonRepo;
import com.pdp.reactors.model.Person;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepo personRepo;

    public List<Person> findAllPersons(){
        return personRepo.findAll();
    }

    public Person findPersonById(Integer id){
        return personRepo.findById(id).orElse(null);
    }

    public Person savePerson(Person person){
        return  personRepo.save(person);
    }



}
