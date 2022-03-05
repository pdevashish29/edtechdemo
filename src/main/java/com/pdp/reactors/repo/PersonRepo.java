package com.pdp.reactors.repo;

import com.pdp.reactors.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person,Integer> {
}
