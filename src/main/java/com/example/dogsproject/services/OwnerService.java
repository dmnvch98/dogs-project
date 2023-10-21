package com.example.dogsproject.services;

import com.example.dogsproject.converter.OwnerConverter;
import com.example.dogsproject.dto.OwnerCreateDto;
import com.example.dogsproject.dto.OwnerResponseDto;
import com.example.dogsproject.exceptions.AppException;
import com.example.dogsproject.models.Dog;
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
    private final DogService dogService;

    @Autowired
    public OwnerService(@Qualifier("ownerRepositoryCriteria") OwnerRepository ownerRepository, OwnerConverter ownerConverter, DogService dogService) {
        this.ownerRepository = ownerRepository;
        this.ownerConverter = ownerConverter;
        this.dogService = dogService;
    }

    public OwnerResponseDto save(OwnerCreateDto dto) {
        List<Dog> dogsList = dogService.mapDogsDtoToDogs(dto.getDogs());
        Owner owner = ownerConverter.createDtoToOwner(dto, dogsList);

        attachDogsToOwner(owner);

        Owner savedOwner = ownerRepository.save(owner);
        List<Long> dogIds = dogService.extractDogIdsFromDogsList(savedOwner.getDogs());

        return ownerConverter.ownerToResponseDto(savedOwner, dogIds);
    }

    public OwnerResponseDto findById(Long id) {
        Owner foundOwner = findOwnerById(id);
        List<Long> dogIds = dogService.extractDogIdsFromDogsList(foundOwner.getDogs());

        return ownerConverter.ownerToResponseDto(foundOwner, dogIds);
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
            .map(owner -> {
                List<Long> dogIds = dogService.extractDogIdsFromDogsList(owner.getDogs());
                return ownerConverter.ownerToResponseDto(owner, dogIds);
            })
            .collect(Collectors.toList());
    }

    public OwnerResponseDto update(OwnerCreateDto dto, Long id) {
        List<Dog> dogsList = dogService.mapDogsDtoToDogs(dto.getDogs());
        Owner owner = ownerConverter.createDtoToOwner(dto, dogsList);
        owner.setId(id);

        Owner savedOwner = ownerRepository.save(owner);
        List<Long> dogIds = dogService.extractDogIdsFromDogsList(savedOwner.getDogs());

        return ownerConverter.ownerToResponseDto(savedOwner, dogIds);
    }

    public void delete(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId);
        if (owner != null) {
            ownerRepository.delete(owner);
        } else {
            throw new AppException("Owner not found. Owner id: " + ownerId, HttpStatus.NOT_FOUND);
        }
    }

    private void attachDogsToOwner(Owner owner) {
        if (owner.getDogs() != null && !owner.getDogs().isEmpty()) {
            owner.getDogs().forEach(dog -> dog.setOwner(owner));
        }
    }

}
