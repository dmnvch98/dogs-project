package com.example.dogsproject.controllers;

import com.example.dogsproject.models.Cat;
import com.example.dogsproject.services.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cats")
@RequiredArgsConstructor
public class CatController {
    private final CatService catService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cat save(@RequestBody Cat cat) {
        return catService.save(cat);
    }
}
