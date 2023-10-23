package com.example.dogsproject.models.abstractClasses;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
    @SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence_name")
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String surName;
}
