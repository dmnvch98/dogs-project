package com.example.dogsproject.services;

import com.example.dogsproject.converter.DogConverter;
import com.example.dogsproject.dto.DogDto;
import com.example.dogsproject.exceptions.AppException;
import com.example.dogsproject.models.Breed;
import com.example.dogsproject.models.Dog;
import com.example.dogsproject.models.Owner;
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
    private final BreedService breedService;
    private final OwnerService ownerService;

    public DogDto save(DogDto dogDto) {
        Breed breed = breedService.getBreedByIdOrThrow(dogDto.getBreedId());
        Owner owner = ownerService.findOwnerById(dogDto.getOwnerId());

        Dog dog = dogConverter.mapDtoToDog(dogDto, breed, owner);

        dogRepository.save(dog);

        return dogConverter.dogToDto(dog);
    }

    public DogDto findById(Long dogId) {
        Dog dog = dogRepository
            .findById(dogId)
            .orElseThrow(() -> new AppException("Dog not found. Dog id: " + dogId, HttpStatus.NOT_FOUND));

        Long ownerId = dog.getOwner().getId();
        Long breedId = dog.getBreed() != null ? dog.getBreed().getId() : null;

        return dogConverter.dogToDto(dog);
    }

    public List<DogDto> findAll() {
        List<Dog> dogs = (List<Dog>) dogRepository.findAll();
        return dogs
            .stream()
            .map(dog -> {
                Long ownerId = dog.getOwner().getId();
                Long breedId = dog.getBreed() != null ? dog.getBreed().getId() : null;

                return dogConverter.dogToDto(dog);
            })
            .collect(Collectors.toList());
    }

    public void delete(Long dogId) {
        Dog dog = dogRepository
            .findById(dogId)
            .orElseThrow(() -> new AppException("Dog not found. Dog id: " + dogId, HttpStatus.NOT_FOUND));
        dogRepository.delete(dog);
    }

    public List<Dog> mapDogsDtoToDogs(List<DogDto> dogsList) {

        return dogsList != null && !dogsList.isEmpty() ? dogsList
            .stream()
            .map(dogDto -> {
                Breed breed = breedService.getBreedByIdOrThrow(dogDto.getBreedId());
                Owner owner = ownerService.findOwnerById(dogDto.getOwnerId());

                return dogConverter.mapDtoToDog(dogDto, breed, owner);
            })
            .collect(Collectors.toList())
            : null;
    }

    public List<Long> extractDogIdsFromDogsList(List<Dog> dogsList) {
        return dogsList != null && !dogsList.isEmpty()
            ? dogsList
            .stream()
            .map(Dog::getId)
            .collect(Collectors.toList())
            : null;
    }
}
