package com.example.dogsproject.models;

import com.example.dogsproject.models.enums.PetVetSpecialization;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PetVet extends Vet {
    private PetVetSpecialization petVetSpecialization;
}
