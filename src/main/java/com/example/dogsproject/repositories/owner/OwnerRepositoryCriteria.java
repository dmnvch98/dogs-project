package com.example.dogsproject.repositories.owner;

import com.example.dogsproject.models.Owner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OwnerRepositoryCriteria {

    @PersistenceContext
    private final EntityManager entityManager;

    public Optional<Owner> findById(Long id) throws EmptyResultDataAccessException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Owner> criteriaQuery = criteriaBuilder.createQuery(Owner.class);

        Root<Owner> root = criteriaQuery.from(Owner.class);

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));

        Query<Owner> query = (Query<Owner>) entityManager.createQuery(criteriaQuery);
        query.setCacheable(true);

        return Optional.of(query.getSingleResult());
    }

    public List<Owner> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Owner> criteriaQuery = criteriaBuilder.createQuery(Owner.class);
        Root<Owner> root = criteriaQuery.from(Owner.class);

        criteriaQuery.select(root);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Modifying
    @Transactional
    public void update(Owner owner) {
        Owner updatedOwner = entityManager.merge(owner); // Get the updated object after merge
        entityManager.flush(); // Synchronize the changes with the database
        entityManager.refresh(updatedOwner); // Refresh the object from the database
    }

    @Modifying
    @Transactional
    public void delete(Owner owner) {
        entityManager.remove(owner);
    }

    @Transactional
    public void save(Owner owner) {
        entityManager.persist(owner);
    }

}
