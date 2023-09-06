package com.example.dogsproject.controllers;

import com.example.dogsproject.dto.DogDto;
import com.example.dogsproject.services.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dogs")
@RequiredArgsConstructor
public class DogController {
    private final DogService dogService;

    @PostMapping
    public DogDto saveDog(@RequestBody DogDto dogDto) {
        return dogService.save(dogDto);
    }
}
