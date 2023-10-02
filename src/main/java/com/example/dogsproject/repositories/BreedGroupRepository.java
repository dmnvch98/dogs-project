package com.example.dogsproject.repositories;

import com.example.dogsproject.models.BreedGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BreedGroupRepository extends CrudRepository<BreedGroup, Long> {
}
