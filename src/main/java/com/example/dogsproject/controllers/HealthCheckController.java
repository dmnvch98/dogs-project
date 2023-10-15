package com.example.dogsproject.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/health")
@CrossOrigin
public class HealthCheckController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void checkHealth() {}
}
