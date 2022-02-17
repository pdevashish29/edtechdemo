package com.pdp.reactors;

import com.github.javafaker.Faker;
import com.pdp.reactors.controller.repo.PersonRepo;
import com.pdp.reactors.model.Person;
import com.pdp.reactors.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class MainTest {

    @Autowired
    private PersonRepo personRepo;


    @Test
    public void checkPerfomance() {
        personRepo.deleteAll();

          int size =10;
          List<Person> personsList1 = getFakePersonList(size);
          List<Person> personList2 =getFakePersonList(size);


        Long t1 =System.currentTimeMillis();
        for (Person p : personsList1) {
            Person save = personRepo.save(p);
            save.setId(null);
        }

        Long t2 =System.currentTimeMillis();
        personList2.stream().parallel().forEach(person -> personRepo.save(person));
        Long t3 = System.currentTimeMillis();
        System.out.println("Classic Time of execution "+ (t2-t1));
        System.out.println("Parallel Stream Time of execution "+ (t3-t2));
        int noOfRecords = (int) personRepo.count();
        Assertions.assertEquals(noOfRecords,(size*2));
    }





    private  List<Person> getFakePersonList(Integer size){
        Faker faker = new Faker();
        return IntStream.range(0, size)
                .mapToObj(i ->  {
                    Person person = new Person();
                    person.setName(faker.name().fullName());
                    person.setAddress(faker.address().fullAddress());
                    person.setAge(faker.number().randomDigitNotZero());
                    return  person;
                })
                .collect(Collectors.toList());
    }



}
