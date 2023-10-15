package com.example.dogsproject.services;

import com.example.dogsproject.converter.DogConverter;
import com.example.dogsproject.dto.DogDto;
import com.example.dogsproject.exceptions.AppException;
import com.example.dogsproject.models.Dog;
import com.example.dogsproject.repositories.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DogService {
    private final DogRepository dogRepository;
    private final DogConverter dogConverter;

    public DogDto save(DogDto dogDto) {
        Dog dog = dogConverter.mapDtoToDog(dogDto);

        return dogConverter.dogToDto(dogRepository.save(dog));
    }

    public DogDto findById(Long dogId) {
        Dog dog = dogRepository
            .findById(dogId)
            .orElseThrow(() -> new AppException("Dog not found. Dog id: " + dogId, HttpStatus.NOT_FOUND));
        return dogConverter.dogToDto(dog);
    }

    public List<DogDto> findAll() {
        List<Dog> dogs = (List<Dog>) dogRepository.findAll();
        return dogs.stream().map(dogConverter::dogToDto).collect(Collectors.toList());
    }

    public void delete(Long dogId) {
        Dog dog = dogRepository
            .findById(dogId)
            .orElseThrow(() -> new AppException("Dog not found. Dog id: " + dogId, HttpStatus.NOT_FOUND));
        dogRepository.delete(dog);
    }
}
