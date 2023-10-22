package com.example.dogsproject.repositories;

import com.example.dogsproject.models.Dog;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface DogRepository extends CrudRepository<Dog, Long> {

}
