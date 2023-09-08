package com.example.dogsproject.controllers;

import com.example.dogsproject.dto.DogDto;
import com.example.dogsproject.services.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dogs")
@RequiredArgsConstructor
public class DogController {
    private final DogService dogService;

    @PostMapping
    public DogDto save(@RequestBody DogDto dogDto) {
        return dogService.save(dogDto);
    }

    @GetMapping("/{dogId}")
    public DogDto get(@PathVariable Long dogId) {
        return dogService.findById(dogId);
    }

    @GetMapping
    public List<DogDto> getAll(@RequestParam Long[] ids) {
        return dogService.findAll(List.of(ids));
    }

    @PutMapping
    public DogDto update(@RequestBody DogDto dogDto)  {
        return dogService.save(dogDto);
    }

    @DeleteMapping("/{dogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long dogId) {
        dogService.delete(dogId);
    }
}
