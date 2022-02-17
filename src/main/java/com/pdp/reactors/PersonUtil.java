package com.pdp.reactors;

import com.github.javafaker.Faker;
import com.pdp.reactors.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class PersonUtil {


    public List<Person> getPersonBySize(int size){
         Faker faker = new Faker();
          return IntStream.range(0, size)
                  .mapToObj(i ->  new Person(null,faker.name().fullName(),faker.address().fullAddress(),faker.number().randomDigitNotZero())).collect(Collectors.toList());
}
}
