package com.example.dogsproject.services;

import com.example.dogsproject.models.PetVet;
import com.example.dogsproject.models.Vet;
import com.example.dogsproject.repositories.PetVetRepository;
import com.example.dogsproject.repositories.VetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetVetService {
    private final PetVetRepository petVetRepository;

    public PetVet save(PetVet petVet) {
        return petVetRepository.save(petVet);
    }
}
