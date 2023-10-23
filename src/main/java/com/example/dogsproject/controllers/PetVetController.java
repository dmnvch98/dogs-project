package com.example.dogsproject.controllers;

import com.example.dogsproject.models.PetVet;
import com.example.dogsproject.services.PetVetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/petvets")
@RequiredArgsConstructor
public class PetVetController {
    private final PetVetService petVetService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PetVet save(@RequestBody PetVet petVet) {
        return petVetService.save(petVet);
    }
}
