package com.example.dogsproject.repositories.owner;

import com.example.dogsproject.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OwnRepositoryNative extends JpaRepository<Owner, Long> {
    @Modifying
    @Transactional
    @Query(
        value = "UPDATE owners SET name = :#{#owner.name}, date_of_birth = :#{#owner.dateOfBirth}, " +
            "phone = :#{#owner.phone} WHERE id = :#{#owner.id}",
        nativeQuery = true
    )
    void update(@Param("owner") Owner owner);

}
