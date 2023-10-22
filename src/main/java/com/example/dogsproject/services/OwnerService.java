package com.example.dogsproject.services;

import com.example.dogsproject.dto.OwnerCreateDto;
import com.example.dogsproject.dto.OwnerResponseDto;
import com.example.dogsproject.models.Owner;

import java.util.List;

public interface OwnerService {
    OwnerResponseDto save(OwnerCreateDto dto);

    OwnerResponseDto findById(Long id);

    Owner findOwnerById(Long id);

    List<OwnerResponseDto> findAll();

    OwnerResponseDto update(OwnerCreateDto dto, Long id);

    void delete(Long ownerId);
}
