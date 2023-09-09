package com.example.dogsproject.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "dogs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "dog_table_generator")
    @TableGenerator(
        name = "dog_table_generator",
        table = "hibernate_sequences",
        pkColumnName = "sequence_name",
        valueColumnName = "next_val")
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "breed_id")
    private Breed breed;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth ;
    private Integer weight;
    private Integer height;
}
