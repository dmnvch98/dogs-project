package com.example.dogsproject.services;

import com.example.dogsproject.models.Vet;
import com.example.dogsproject.repositories.VetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VetService {
    private final VetRepository vetRepository;

    public Vet save(Vet vet) {
        return vetRepository.save(vet);
    }
}
