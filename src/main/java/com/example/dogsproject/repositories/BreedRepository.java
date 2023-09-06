package com.example.dogsproject.repositories;

import com.example.dogsproject.models.Breed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreedRepository extends CrudRepository<Breed, Long> {
}
