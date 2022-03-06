package com.pdp.reactors;

import com.pdp.reactors.controller.PersonController;
import com.pdp.reactors.model.PersonList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PeronControllerTest {

    @LocalServerPort
    private  int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() {
          PersonList list= testRestTemplate.getForObject("http://localhost:"+port+"/v1/persons", PersonList.class);
          System.out.println(list.getPersons().size());
          Assertions.assertNotNull(testRestTemplate);
    }
}
