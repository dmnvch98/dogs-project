package com.example.dogsproject.controllers;

import com.example.dogsproject.converter.OwnerConverter;
import com.example.dogsproject.dto.OwnerCreateDto;
import com.example.dogsproject.dto.OwnerResponseDto;
import com.example.dogsproject.models.Owner;
import com.example.dogsproject.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;
    private final OwnerConverter ownerConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerResponseDto createOwner(@RequestBody OwnerCreateDto dto) {
        Owner savedOwner = ownerService.save(dto);
        return ownerConverter.ownerToResponseDto(savedOwner);
    }

    @GetMapping("/{id}")
    public OwnerResponseDto getOwnerById(@PathVariable Long id) {
        Owner owner = ownerService.findById(id);
        return ownerConverter.ownerToResponseDto(owner);
    }

    @GetMapping
    public List<OwnerResponseDto> getAllOwners() {
        List<Owner> ownersList = ownerService.findAll();
        return ownersList
            .stream()
            .map(ownerConverter::ownerToResponseDto)
            .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public OwnerResponseDto updateOwner(@PathVariable Long id, @RequestBody OwnerCreateDto dto) {
        Owner updatedOwner = ownerService.update(dto, id);
        return ownerConverter.ownerToResponseDto(updatedOwner);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOwner(@PathVariable Long id) {
        Owner owner = ownerService.findById(id);
        ownerService.delete(owner);
    }
}
