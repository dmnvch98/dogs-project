package com.example.dogsproject.models;

import com.example.dogsproject.models.abstractClasses.Person;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.LocalDate;
import java.util.List;

@Table(name = "owners")
@Entity
@Getter
@Setter
@NoArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "owners")
public class Owner extends Person {
    @Column(nullable = false)
    private String phone;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "dogs")
    private List<Dog> dogs;
    private LocalDate dateOfBirth;
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
