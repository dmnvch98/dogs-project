package com.example.dogsproject.models;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "breeds")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Breed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bread_group_id")
    private BreedGroup breedGroup;
    private String name;

    public Breed(String name) {
        this.name = name;
    }
}