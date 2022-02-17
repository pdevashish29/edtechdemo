package com.pdp.reactors;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MainTest {

    @Autowired
    public void loadContext(){
        log.info("context loaded ");
    }

    @Test
    public void checkPerfomance(){
        Assertions.assertTrue(true);
    }

}
