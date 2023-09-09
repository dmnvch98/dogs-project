package com.example.dogsproject.services;

import com.example.dogsproject.converter.OwnerConverter;
import com.example.dogsproject.dto.OwnerCreateDto;
import com.example.dogsproject.exceptions.AppException;
import com.example.dogsproject.models.Owner;
import com.example.dogsproject.repositories.owner.OwnerRepositoryHQL;
import com.example.dogsproject.repositories.owner.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerConverter ownerConverter;

    @Autowired
    public OwnerService(@Qualifier("ownerRepositoryHQL") OwnerRepositoryHQL ownerRepository, OwnerConverter ownerConverter) {
        this.ownerRepository = ownerRepository;
        this.ownerConverter = ownerConverter;
    }

    public Owner save(OwnerCreateDto dto) {
        Owner owner = ownerConverter.createDtoToOwner(dto);

        return ownerRepository.save(owner);
    }

    public Owner findById(Long id) {
        try {
            return ownerRepository.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new AppException("Owner not found. Owner id: " + id, HttpStatus.NOT_FOUND);
        }

    }

    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    public Owner update(OwnerCreateDto dto, Long id) {
        Owner owner = ownerConverter.createDtoToOwner(dto);
        owner.setId(id);
        return ownerRepository.save(owner);
    }

    public void delete(Owner owner) {
        ownerRepository.delete(owner);
    }

}
