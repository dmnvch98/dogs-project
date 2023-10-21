package com.example.dogsproject.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Table(name = "owners")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@Builder
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owner_sequence")
    @SequenceGenerator(name = "owner_sequence", sequenceName = "owner_sequence_name")
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "dogs")
    private List<Dog> dogs;
    private LocalDate dateOfBirth;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Owner owner = (Owner) o;
        return id != null && Objects.equals(id, owner.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
