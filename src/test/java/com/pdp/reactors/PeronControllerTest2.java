package com.pdp.reactors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
public class PeronControllerTest2 {


    @Autowired
    private MockMvc mockMvc;

    @Test
    void getPerosn() throws Exception {
        mockMvc.perform(get("/v1/persons")
                        .header("ACCEPT","application/json"))
                .andDo(result -> {
                    System.out.println(result.getResponse().getBufferSize());
                    System.out.println(result.getResponse().getCharacterEncoding());
                    //System.out.println(result.getResponse().getContentAsString());
                    System.out.println(result.getResponse().getContentLength());
                    System.out.println(result.getResponse().getContentLengthLong());
                    System.out.println(result.getResponse().getContentType());
                })
                        .andExpect(status().isOk());
    }
}
