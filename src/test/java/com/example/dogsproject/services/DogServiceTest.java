package com.example.dogsproject.services;

import com.example.dogsproject.dto.DogDto;
import com.example.dogsproject.dto.OwnerCreateDto;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import java.time.LocalDate;


@SpringBootTest
class DogServiceTest {

    @Autowired
    private DogService dogService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void findById() {
        OwnerCreateDto owner = OwnerCreateDto.builder()
            .name("Owner")
            .dateOfBirth(LocalDate.now())
            .phone("1231231")
            .build();


        DogDto dogDto = DogDto.builder()
            .name("Test")
            .breedId(2L)
            .ownerId(1L)
            .weight(20)
            .height(50)
            .build();

        Long dogId = dogService.save(dogDto).getId();
        dogService.findById(dogId);
    }
}