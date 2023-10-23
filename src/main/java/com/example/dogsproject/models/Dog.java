package com.example.dogsproject.models;

import com.example.dogsproject.models.abstractClasses.Animal;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "dogs")
@DiscriminatorValue("DOG")
public class Dog extends Animal {
    @ManyToOne
    @JoinColumn(name = "breed_id")
    private Breed breed;

}
