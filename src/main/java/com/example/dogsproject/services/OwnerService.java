package com.example.dogsproject.services;

import com.example.dogsproject.converter.OwnerConverter;
import com.example.dogsproject.dto.OwnerCreateDto;
import com.example.dogsproject.dto.OwnerResponseDto;
import com.example.dogsproject.exceptions.AppException;
import com.example.dogsproject.models.Owner;
import com.example.dogsproject.repositories.owner.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerConverter ownerConverter;

    @Autowired
    public OwnerService(@Qualifier("ownerRepositoryCriteria") OwnerRepository ownerRepository, OwnerConverter ownerConverter) {
        this.ownerRepository = ownerRepository;
        this.ownerConverter = ownerConverter;
    }

    public OwnerResponseDto save(OwnerCreateDto dto) {
        Owner owner = ownerConverter.createDtoToOwner(dto);

        if (owner.getDogs() != null && !owner.getDogs().isEmpty()) {
            owner.getDogs().forEach(dog -> dog.setOwner(owner));
        }
        return ownerConverter.ownerToResponseDto(ownerRepository.save(owner));
    }

    public OwnerResponseDto findById(Long id) {
        return ownerConverter.ownerToResponseDto(findOwnerById(id));
    }

    public Owner findOwnerById(Long id) {
        try {
            return ownerRepository.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new AppException("Owner not found. Owner id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    public List<OwnerResponseDto> findAll() {
        return ownerRepository
            .findAll()
            .stream()
            .map(ownerConverter::ownerToResponseDto)
            .collect(Collectors.toList());
    }

    public OwnerResponseDto update(OwnerCreateDto dto, Long id) {
        Owner owner = ownerConverter.createDtoToOwner(dto);
        owner.setId(id);
        return ownerConverter.ownerToResponseDto(ownerRepository.save(owner));
    }

    public void delete(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId);
        if (owner != null) {
            ownerRepository.delete(owner);
        } else {
            throw new AppException("Owner not found. Owner id: " + ownerId, HttpStatus.NOT_FOUND);
        }
    }

}
