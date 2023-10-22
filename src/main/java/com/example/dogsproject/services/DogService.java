package com.example.dogsproject.services;

import com.example.dogsproject.dto.DogDto;

import java.util.List;

public interface DogService {
    DogDto save(DogDto dogDto);
    DogDto findById(Long dogId);
    List<DogDto> findAll();
    void delete(Long dogId);
}
