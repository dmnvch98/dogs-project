package com.example.dogsproject.services;

import com.example.dogsproject.models.Cat;
import com.example.dogsproject.repositories.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatService {
    private final CatRepository catRepository;

    public Cat save(Cat cat) {
        return catRepository.save(cat);
    }
}
