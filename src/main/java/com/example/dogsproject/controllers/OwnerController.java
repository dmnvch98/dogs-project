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
@CrossOrigin
public class OwnerController {
    private final OwnerService ownerService;
    private final OwnerConverter ownerConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerResponseDto createOwner(@RequestBody OwnerCreateDto dto) {
        return ownerService.save(dto);
    }

    @GetMapping("/{id}")
    public OwnerResponseDto getOwnerById(@PathVariable Long id) {
        return ownerService.findById(id);
    }

    @GetMapping
    public List<OwnerResponseDto> getAllOwners() {
        return ownerService.findAll();
    }

    @PutMapping("/{id}")
    public OwnerResponseDto updateOwner(@PathVariable Long id, @RequestBody OwnerCreateDto dto) {
        return ownerService.update(dto, id);
    }

    @DeleteMapping("/{ownerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long ownerId) {
        ownerService.delete(ownerId);
    }
}
