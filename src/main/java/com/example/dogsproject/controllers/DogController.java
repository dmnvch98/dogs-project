package com.example.dogsproject.controllers;

import com.example.dogsproject.converter.DogConverter;
import com.example.dogsproject.dto.DogDto;
import com.example.dogsproject.models.Dog;
import com.example.dogsproject.services.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/dogs")
@RequiredArgsConstructor
public class DogController {
    private final DogService dogService;
    private final DogConverter dogConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DogDto save(@RequestBody DogDto dogDto) {
        Dog savedDog = dogService.save(dogConverter.mapDtoToDog(dogDto));
        return dogConverter.dogToDto(savedDog);
    }

    @GetMapping("/{dogId}")
    public DogDto get(@PathVariable Long dogId) {
        Dog dog = dogService.findById(dogId);
        return dogConverter.dogToDto(dog);
    }

    @GetMapping
    public List<DogDto> getAll() {
        List<Dog> dogs = dogService.findAll();
        return dogs.stream().map(dogConverter::dogToDto).collect(Collectors.toList());
    }

    @PutMapping
    public DogDto update(@RequestBody DogDto dogDto) {
        Dog updatedDog = dogService.save(dogConverter.mapDtoToDog(dogDto));
        return dogConverter.dogToDto(updatedDog);
    }

    @DeleteMapping("/{dogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long dogId) {
        dogService.delete(dogId);
    }
}
