package com.example.dogsproject.repositories.owner;

import com.example.dogsproject.models.Owner;
import jakarta.persistence.TypedQuery;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OwnerRepositoryNative extends OwnerRepository {

    @Override
    @Transactional
    public Owner findById(Long id) throws EmptyResultDataAccessException {
        TypedQuery<Owner> query = entityManager.createQuery("SELECT o FROM Owner o WHERE o.id = :id", Owner.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public List<Owner> findAll() {
        TypedQuery<Owner> query = entityManager.createQuery("SELECT o FROM Owner o", Owner.class);
        return query.getResultList();
    }
}
