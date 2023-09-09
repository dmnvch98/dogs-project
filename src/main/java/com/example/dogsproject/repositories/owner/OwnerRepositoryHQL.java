package com.example.dogsproject.repositories.owner;

import com.example.dogsproject.models.Owner;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OwnerRepositoryHQL extends OwnerRepository {
    @Override
    public Owner findById(Long id) throws EmptyResultDataAccessException {
        List<Owner> owners = entityManager.createQuery("FROM Owner WHERE id = :id", Owner.class)
                .setParameter("id", id)
                .getResultList();

        if (owners.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }

        return owners.get(0);
    }

    @Override
    public List<Owner> findAll() {
        return entityManager.createQuery("FROM Owner", Owner.class).getResultList();
    }
}
