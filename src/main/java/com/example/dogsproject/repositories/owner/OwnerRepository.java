package com.example.dogsproject.repositories.owner;

import com.example.dogsproject.models.Owner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class OwnerRepository {
    @PersistenceContext
    protected EntityManager entityManager;

    public abstract Owner findById(Long id);

    public abstract List<Owner> findAll();

    @Transactional
    public Owner save(Owner owner) {
        entityManager.persist(owner);
        return owner;
    }

    @Transactional
    public void delete(Owner owner) {
        entityManager.remove(owner);
    }
}
