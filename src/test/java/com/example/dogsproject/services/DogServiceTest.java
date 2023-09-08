package com.example.dogsproject.services;

import com.example.dogsproject.dto.DogDto;
import com.example.dogsproject.models.Dog;
import lombok.Builder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
class DogServiceTest {

    @Autowired
    private DogService dogService;

    private static DogDto dogDto;

    @BeforeAll
    static void initDog() {
        dogDto = DogDto.builder()
            .dateOfBirth(LocalDate.of(2020, 12, 10))
            .name("Dog")
            .height(50)
            .weight(25)
            .build();
    }

    @Test
    void save() {
        DogDto savedDog = dogService.save(dogDto);

        Assertions.assertNotNull(savedDog.getId());
    }

    @Test
    void findById() {
        DogDto savedDog = dogService.save(dogDto);

        DogDto foundDog = dogService.findById(1L);

        Assertions.assertEquals(savedDog, foundDog);
    }

    @Test
    void findAll_No_Filter() {
        DogDto dogDto2 = DogDto.builder()
            .dateOfBirth(LocalDate.of(2019, 12, 10))
            .name("Dog 2")
            .height(50)
            .weight(25)
            .build();

        DogDto savedDog1 = dogService.save(dogDto);
        DogDto savedDog2 = dogService.save(dogDto2);

        List<DogDto> foundDogs = dogService.findAll(null);

        Assertions.assertNotNull(foundDogs);

        Assertions.assertEquals(List.of(savedDog1, savedDog2), foundDogs);
    }

    @Test
    void findAll_Filter() {
        DogDto dogDto2 = DogDto.builder()
            .dateOfBirth(LocalDate.of(2019, 12, 10))
            .name("Dog 2")
            .height(50)
            .weight(25)
            .build();

        DogDto dogDto3 = DogDto.builder()
            .dateOfBirth(LocalDate.of(2018, 12, 10))
            .name("Dog 2")
            .height(50)
            .weight(25)
            .build();

        DogDto savedDog1 = dogService.save(dogDto);
        DogDto savedDog2 = dogService.save(dogDto2);
        dogService.save(dogDto3);

        List<DogDto> foundDogs = dogService.findAll(List.of(savedDog1.getId(), savedDog2.getId()));

        Assertions.assertNotNull(foundDogs);

        Assertions.assertEquals(List.of(savedDog1, savedDog2), foundDogs);
    }

    @Test
    void delete() {
        DogDto savedDog = dogService.save(dogDto);
        Assertions.assertDoesNotThrow(() -> dogService.delete(savedDog.getId()));
    }

}