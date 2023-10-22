package com.example.dogsproject.repositories.owner;

import com.example.dogsproject.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OwnRepositoryHQL extends JpaRepository<Owner, Long> {
    @Modifying
    @Transactional
    @Query(
        "UPDATE Owner SET name = :#{#owner.name}, dateOfBirth = :#{#owner.dateOfBirth}, phone = :#{#owner.phone} " +
        "WHERE id = :#{#owner.id}"
    )
    void update(@Param("owner") Owner owner);

}
