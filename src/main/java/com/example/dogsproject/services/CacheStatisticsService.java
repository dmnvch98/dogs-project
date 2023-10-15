package com.example.dogsproject.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CacheStatisticsService {
    @PersistenceContext
    private EntityManager entityManager;

    public void printCacheStatistics() {
        Session session = entityManager.unwrap(Session.class);

        org.hibernate.stat.Statistics statistics = session.getSessionFactory().getStatistics();

        if (!statistics.isStatisticsEnabled()) {
            statistics.setStatisticsEnabled(true);
        }

        log.info("Cache Hit Count: " + statistics.getSecondLevelCacheHitCount());
        log.info("Cache Miss Count: " + statistics.getSecondLevelCacheMissCount());
        log.info("Cache Put Count: " + statistics.getSecondLevelCachePutCount());
    }
}
