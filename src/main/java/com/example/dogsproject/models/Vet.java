package com.example.dogsproject.models;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vet extends Person {
    private VetSpecialization vetSpecialization;
    private LocalDate startDate;

    public Vet(Long id, String name, String surName, VetSpecialization vetSpecialization, LocalDate startDate) {
        super(id, name, surName);
        this.vetSpecialization = vetSpecialization;
        this.startDate = startDate;
    }

    public Vet(String name, String surName, VetSpecialization vetSpecialization, LocalDate startDate) {
        super(name, surName);
        this.vetSpecialization = vetSpecialization;
        this.startDate = startDate;
    }
}
