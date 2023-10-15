package com.example.dogsproject.repositories.owner;

import com.example.dogsproject.models.Dog;
import com.example.dogsproject.models.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@Disabled
class OwnerRepositoryTest {

    @Autowired
    private List<OwnerRepository> ownerRepositories;

    @Test
    void save() {
        ownerRepositories.forEach(ownerRepository -> {
            Owner savedOwner = ownerRepository.save(createOwner());
            Assertions.assertNotNull(savedOwner);
        });
    }

    @Test
    void findById() {
        ownerRepositories.forEach(ownerRepository -> {
            Owner savedOwner = ownerRepository.save(createOwner());

            Owner foundOwner = ownerRepository.findById(savedOwner.getId());

            assertEquals(savedOwner, foundOwner);
        });
    }

    @Test
    void findAll() {
        ownerRepositories.forEach(ownerRepository -> {
            Owner savedOwner1 = ownerRepository.save(createOwner());
            Owner savedOwner2 = ownerRepository.save(createOwner());

            List<Owner> foundOwners = ownerRepository.findAll();
            assertTrue(foundOwners.containsAll(List.of(savedOwner1, savedOwner2)));
        });
    }

    private Owner createOwner() {
        Owner owner = new Owner();
        owner.setName("OWner");
        owner.setPhone("12312");
        owner.setDateOfBirth(LocalDate.of(1998, 9, 11));

        Dog dog = new Dog();
        dog.setOwner(owner);
        dog.setName("name dog");
        dog.setWeight(12);
        dog.setHeight(123);

        owner.setDogs(List.of(dog));
        return owner;
    }

}