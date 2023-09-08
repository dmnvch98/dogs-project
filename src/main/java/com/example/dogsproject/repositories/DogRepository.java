package com.example.dogsproject.repositories;

import com.example.dogsproject.models.Dog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DogRepository extends CrudRepository <Dog, Long> {
}
