package com.example.dogsproject.listeners;

import com.example.dogsproject.services.BreedService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
    private final BreedService breedService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        breedService.fetchBreedsFromRemoteRepositoryAndSave();
    }
}
