package com.pdp.reactors.service;


import com.pdp.reactors.model.Person;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

  List<Person> persons = new ArrayList<>();
    {
        persons.add(new Person("101","Parashar","delhi", 25));
        persons.add(new Person("102","Devashish","delhi", 25));
    }



    public List<Person> findAllPersons(){
        return  this.persons;
    }

    public Person findPersonById(String id){

        return this.persons.stream()
                .filter(item -> item.getId().equalsIgnoreCase(id)).collect(Collectors.toList())
                .get(0);

    }

    public Person savePerson(Person person){
         person.setId(new Random(100).nextInt()+"");
          this.persons.add(person);
          return person;
    }



}
