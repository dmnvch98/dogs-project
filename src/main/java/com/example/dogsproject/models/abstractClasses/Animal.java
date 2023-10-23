package com.example.dogsproject.models.abstractClasses;

import com.example.dogsproject.models.Owner;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "animal_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "dog_table_generator")
    @TableGenerator(
        name = "dog_table_generator",
        table = "hibernate_sequences",
        pkColumnName = "sequence_name",
        valueColumnName = "next_val")
    Long id;
    @Column(nullable = false)
    String name;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth ;
    private Integer weight;
    private Integer height;

}
