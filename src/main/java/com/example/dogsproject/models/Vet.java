package com.example.dogsproject.models;

import com.example.dogsproject.models.abstractClasses.Person;
import com.example.dogsproject.models.enums.VetSpecialization;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Vet extends Person {
    private VetSpecialization vetSpecialization;
    private LocalDate startDate;

}
