package com.example.dogsproject;

import com.example.dogsproject.models.Owner;
import com.example.dogsproject.repositories.owner.OwnerRepositoryCriteria;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext
@DBRider
public class HQLCache {
//    @Autowired
//    private OwnerRepositoryCriteria ownerRepositoryCriteria;
//
//    @Test
//    @DataSet("datasets/secondLevelCache.xml")
//    void test() {
//        Owner owner = ownerRepositoryCriteria.findById(1L);
//        System.out.println("===================== HERE =====================");
//        Owner cachedOwner = ownerRepositoryCriteria.findById(1L);
//    }
}
