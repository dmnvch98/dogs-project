package com.example.dogsproject.repositories;

import com.example.dogsproject.models.Cat;
import org.springframework.data.repository.CrudRepository;

public interface CatRepository extends CrudRepository<Cat, Long> {
}
