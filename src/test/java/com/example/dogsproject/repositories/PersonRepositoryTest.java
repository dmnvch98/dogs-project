package com.example.dogsproject.repositories;

import com.example.dogsproject.models.Vet;
import com.example.dogsproject.models.VetSpecialization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest
@DirtiesContext
@ActiveProfiles("default")
class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    void save() {
        Vet vet = new Vet("Name", "Surname", VetSpecialization.RADIOLOGY, LocalDate.now());

        personRepository.save(vet);

        Assertions.assertNotNull(vet.getId());
    }
}