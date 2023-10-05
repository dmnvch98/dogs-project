package com.example.dogsproject;

import com.example.dogsproject.models.Dog;
import com.example.dogsproject.models.Owner;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext
@DBRider
public class SecondLevelCacheTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DataSet("datasets/secondLevelCache.xml")
    public void secondLevelCache() {
        Session session = entityManager.unwrap(Session.class);

        Owner owner = session.find(Owner.class, 1L);

        org.hibernate.Cache secondLevelCache = session.getSessionFactory().getCache();

        Assertions.assertTrue(secondLevelCache.containsEntity(Owner.class, 1L));

        session.clear();

        System.out.println("I am here");
        Owner cachedOwner = session.get(Owner.class, 1L);
    }

    @Test
    @DataSet("datasets/collectionCache.xml")
    public void collectionCache() {
        Session session = entityManager.unwrap(Session.class);

        Dog foundDog = session.find(Dog.class, 1L);

        org.hibernate.Cache secondLevelCache = session.getSessionFactory().getCache();

        Assertions.assertTrue(secondLevelCache.containsEntity(Dog.class, 1L));

        session.clear();

        System.out.println("==== GETTING CACHED DOG ====");

        Dog cached = session.find(Dog.class, 1L);

        System.out.println("CACHED DOG ID: " + cached.getId());


    }
}
