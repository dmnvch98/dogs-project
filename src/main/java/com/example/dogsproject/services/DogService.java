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

@RequiredArgsConstructor
@Service
public class DogService {
    private final DogRepository dogRepository;
    private final DogConverter dogConverter;

    public DogDto save(DogDto dogDto) {
        Dog dog = dogConverter.mapCreateDtoToDog(dogDto);
        Dog savedDog = dogRepository.save(dog);
        return dogConverter.dogToResponseDto(savedDog);
    }

    public DogDto findById(Long dogId) {
        return dogRepository
            .findById(dogId)
            .map(dogConverter::dogToResponseDto)
            .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Dog not found"));
    }

    public List<DogDto> findAll(List<Long> dogIds) {
        List<Dog> dogList = (List<Dog>) dogRepository.findAll();

        return dogList
            .stream()
            .filter(dog -> dogIds == null || dogIds.contains(dog.getId()))
            .map(dogConverter::dogToResponseDto)
            .collect(Collectors.toList());
    }

    public void delete(Long dogId) {
        Dog dog = dogRepository
            .findById(dogId)
            .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Dog not found"));
        dogRepository.delete(dog);
    }
}
