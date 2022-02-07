package com.pdp.reactors.controller;

import com.pdp.reactors.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class EmployeeController {

    @GetMapping("/employees/{id}")
    public Employee getEmployee(Integer id){
        return Employee.builder().name("Parashar Devashish").id(101).build();
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return   Arrays.asList(
                        Employee.builder().name("Parashar DevashIsh").id(101).build(),
                        Employee.builder().name("Mohan Das ").id(102).build(),
                        Employee.builder().name("Karam Chandra Ghandhi").id(103).build());

    }
}
