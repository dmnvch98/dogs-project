package com.example.dogsproject.repositories;

import com.example.dogsproject.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
