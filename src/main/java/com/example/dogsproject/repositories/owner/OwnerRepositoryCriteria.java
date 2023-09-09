package com.example.dogsproject.repositories.owner;

import com.example.dogsproject.models.Owner;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OwnerRepositoryCriteria extends OwnerRepository {

    @Override
    public Owner findById(Long id) throws EmptyResultDataAccessException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Owner> criteriaQuery = criteriaBuilder.createQuery(Owner.class);
        Root<Owner> root = criteriaQuery.from(Owner.class);

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public List<Owner> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Owner> criteriaQuery = criteriaBuilder.createQuery(Owner.class);
        Root<Owner> root = criteriaQuery.from(Owner.class);

        criteriaQuery.select(root);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
