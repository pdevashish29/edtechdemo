package com.pdp.reactors.service;


import com.pdp.reactors.PersonUtil;
import com.pdp.reactors.model.PersonList;
import com.pdp.reactors.repo.PersonRepo;
import com.pdp.reactors.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private PersonUtil personUtil;


    public PersonList findAllPersons(){
        System.out.println("####  PersonService findAllPersons");
        PersonList personList = new PersonList();
        personList.setPersons(personRepo.findAll());
        return personList;
    }

    public Person findPersonById(Integer id){
        return personRepo.findById(id).orElse(null);
    }

    public Person savePerson(Person person){
        return  personRepo.save(person);
    }


    public ResponseEntity savePersons() {
        List<Person> persons = personUtil.getPersonBySize(200);
        System.out.println(persons.size());

        Long t1 =System.currentTimeMillis();
        for (Person person : persons) {
            personRepo.save(person);
        }
        Long t2 =System.currentTimeMillis();

        persons.stream().parallel().forEach(person -> personRepo.save(person));
        Long t3 = System.currentTimeMillis();

        System.out.println("Classic Time of execution "+ (t2-t1));
        System.out.println("Parallel Stream Time of execution "+ (t2-t1));

        return  ResponseEntity.ok(null);

    }

}
