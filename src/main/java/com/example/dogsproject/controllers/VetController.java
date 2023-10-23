package com.example.dogsproject.controllers;

import com.example.dogsproject.models.Vet;
import com.example.dogsproject.services.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vets")
@RequiredArgsConstructor
public class VetController {
    private final VetService vetService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Vet save(@RequestBody Vet vet) {
        return vetService.save(vet);
    }
}
