package com.example.dogsproject.listeners;

import com.example.dogsproject.services.BreedService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationStartupListener {
    private final BreedService breedService;
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReadyEvent() {
        breedService.fetchBreedsFromRemoteRepositoryAndSave();

    }
}
