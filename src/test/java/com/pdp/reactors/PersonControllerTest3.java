package com.pdp.reactors;

import com.pdp.reactors.controller.PersonController;
import com.pdp.reactors.model.Person;
import com.pdp.reactors.model.PersonList;
import com.pdp.reactors.service.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
public class PersonControllerTest3 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService service;

    @Test
    void getPersonTest() throws Exception {
        PersonList list = new PersonList();
        Person person = new Person();
        person.setId(101);
        person.setName("Parashar");
        person.setAge(25);
        person.setAddress("Delhi");
        list.setPersons(Arrays.asList(person));
        Mockito.when(service.findAllPersons()).thenReturn(list);
        mockMvc
                .perform(get("/v1/persons")
                        .accept(MediaType.APPLICATION_JSON))
                //.header("ACCEPT","application/json"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.persons").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.persons[*].id").isNotEmpty())
                .andDo(print())
        .andExpect(status().isOk());

    }
}
