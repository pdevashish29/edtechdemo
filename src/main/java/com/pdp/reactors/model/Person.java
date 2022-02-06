package com.pdp.reactors.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {


    private String id;
    private String name;
    private String address;
    private  Integer age;

//    @DateTimeFormat(pattern = DateTimeFormat.)
//    private LocalDateTime createdDate= LocalDateTime.now();

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    private Date createdDate = new Date();




}
