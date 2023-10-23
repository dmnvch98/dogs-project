package com.example.dogsproject.repositories.owner;

import com.example.dogsproject.models.Owner;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@DirtiesContext
@DBRider
@ActiveProfiles("test")
class OwnerRepositoryCriteriaTest {

    @Autowired
    OwnerRepositoryCriteria ownerRepositoryCriteria;

    @Autowired
    OwnRepositoryNative ownRepositoryNative;

    @Autowired
    OwnRepositoryHQL ownRepositoryHQL;

    @Test
    @DataSet("datasets/collectionCache.xml")
    void findById() {
        Owner owner = ownRepositoryHQL.findById(1L).orElseThrow();
        assertFalse(owner.getDogs().isEmpty());
//        System.out.println(owner.getDogs());
    }


}