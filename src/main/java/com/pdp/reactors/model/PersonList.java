package com.pdp.reactors.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@Data
public class PersonList {

    private List<Person> person;

}
