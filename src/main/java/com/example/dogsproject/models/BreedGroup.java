package com.example.dogsproject.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "breed_groups")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BreedGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String name;

    @OneToMany(mappedBy = "breedGroup", cascade = CascadeType.ALL)
    private List<Breed> subBreeds;

    public BreedGroup(String name) {
        this.name = name;
    }
}