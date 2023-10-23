package com.example.dogsproject.models;

import com.example.dogsproject.models.abstractClasses.Animal;
import com.example.dogsproject.models.enums.AnimalColor;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("CAT")
public class Cat extends Animal {
    private AnimalColor animalColor;
}
