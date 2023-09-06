package com.example.dogsproject.services;

import com.example.dogsproject.converter.DogConverter;
import com.example.dogsproject.dto.DogDto;
import com.example.dogsproject.models.Dog;
import com.example.dogsproject.repositories.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DogService {
    private final DogRepository dogRepository;
    private final DogConverter dogConverter;

    public DogDto save(DogDto dogDto) {
        Dog dog = dogConverter.mapCreateDtoToDog(dogDto);
        Dog savedDog =  dogRepository.save(dog);
        return dogConverter.dogToResponseDto(savedDog);
    }
}
