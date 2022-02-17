package com.pdp.reactors.model;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table
@Builder
public class Person {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String address;
    private  Integer age;

//    @DateTimeFormat(pattern = DateTimeFormat.)
//    private LocalDateTime createdDate= LocalDateTime.now();

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    private Date createdDate = new Date();




}
