package com.example.dogsproject.services;

import com.example.dogsproject.converter.DogConverter;
import com.example.dogsproject.dto.DogDto;
import com.example.dogsproject.models.Breed;
import com.example.dogsproject.models.Dog;
import com.example.dogsproject.models.Owner;
import com.example.dogsproject.repositories.DogRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DogService {
    private final DogRepository dogRepository;
    private final DogConverter dogConverter;
    private final BreedService breedService;
    private final OwnerService ownerService;
    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    public DogDto save(DogDto dogDto) {
        Breed breed = breedService.getBreedByIdOrThrow(dogDto.getBreedId());
        Owner owner = ownerService.findOwnerById(dogDto.getOwnerId());

        Dog dog = dogConverter.mapDtoToDog(dogDto, owner, breed);

        dogRepository.save(dog);

        entityManager.refresh(owner);

        return dogConverter.dogToDto(dog);
    }

    public DogDto findById(Long dogId) {
        Dog dog = dogRepository
            .findById(dogId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dog not found. Dog id: " + dogId));

        return dogConverter.dogToDto(dog);
    }

    public List<DogDto> findAll() {
        List<Dog> dogs = (List<Dog>) dogRepository.findAll();
        return dogs
            .stream()
            .map(dogConverter::dogToDto)
            .collect(Collectors.toList());
    }

    public void delete(Long dogId) {
        Dog dog = dogRepository
            .findById(dogId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dog not found. Dog id: " + dogId));
        dogRepository.delete(dog);
    }
}
