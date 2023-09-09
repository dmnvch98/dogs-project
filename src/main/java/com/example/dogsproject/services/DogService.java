package com.example.dogsproject.services;

import com.example.dogsproject.converter.DogConverter;
import com.example.dogsproject.exceptions.AppException;
import com.example.dogsproject.models.Dog;
import com.example.dogsproject.models.Owner;
import com.example.dogsproject.repositories.DogRepository;
import com.example.dogsproject.repositories.owner.OwnerRepositoryHQL;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DogService {
    private final DogRepository dogRepository;
    private final OwnerRepositoryHQL ownerRepository;
    private final DogConverter dogConverter;

    public Dog save(Dog dog) {
        Dog savedDog = dogRepository.save(dog);

        Owner dogOwner = savedDog.getOwner();
        List<Dog> ownerDogs = dogOwner.getDogs();
        ownerDogs.add(savedDog);
        ownerRepository.save(dogOwner);
        return savedDog;
    }

    public Dog findById(Long dogId) {
        return dogRepository
            .findById(dogId)
            .orElseThrow(() -> new AppException("Dog not found. Dog id: " + dogId, HttpStatus.NOT_FOUND));
    }

    public List<Dog> findAll() {
        return (List<Dog>) dogRepository.findAll();
    }

    public void delete(Long dogId) {
        Dog dog = dogRepository
            .findById(dogId)
            .orElseThrow(() -> new AppException("Dog not found. Dog id: " + dogId, HttpStatus.NOT_FOUND));
        dogRepository.delete(dog);
    }

}
