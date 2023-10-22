package com.example.dogsproject.services;

import com.example.dogsproject.converter.DogConverter;
import com.example.dogsproject.converter.OwnerConverter;
import com.example.dogsproject.dto.DogDto;
import com.example.dogsproject.dto.OwnerCreateDto;
import com.example.dogsproject.dto.OwnerResponseDto;
import com.example.dogsproject.models.Breed;
import com.example.dogsproject.models.Dog;
import com.example.dogsproject.models.Owner;
import com.example.dogsproject.repositories.owner.OwnerRepositoryCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepositoryCriteria ownRepository;
    private final OwnerConverter ownerConverter;
    private final BreedService breedService;
    private final DogConverter dogConverter;

    public OwnerResponseDto save(OwnerCreateDto dto) {
        Owner owner = ownerConverter.createDtoToOwner(dto);

        List<Dog> dogsList = getDogsFromDogsDto(dto.getDogs(), owner);

        owner.setDogs(dogsList);

        ownRepository.save(owner);

        List<Long> dogIds = getDogIdsFromDogsList(owner.getDogs());

        return ownerConverter.ownerToResponseDto(owner, dogIds);
    }

    public OwnerResponseDto findById(Long id) {
        Owner foundOwner = findOwnerById(id);
        List<Long> dogIds = getDogIdsFromDogsList(foundOwner.getDogs());

        return ownerConverter.ownerToResponseDto(foundOwner, dogIds);
    }

    public Owner findOwnerById(Long id) {
        return ownRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found. Owner id: " + id));
    }

    public List<OwnerResponseDto> findAll() {
        return ownRepository
            .findAll()
            .stream()
            .map(owner -> {
                List<Long> dogIds = getDogIdsFromDogsList(owner.getDogs());
                return ownerConverter.ownerToResponseDto(owner, dogIds);
            })
            .collect(Collectors.toList());
    }

    public OwnerResponseDto update(OwnerCreateDto dto, Long id) {
        if ((dto.getName() == null || dto.getName().isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is mandatory");
        } else if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id is mandatory");
        } else {
            dto.setId(id);
            ownRepository.update(ownerConverter.createDtoToOwner(dto));

            Owner owner = findOwnerById(id);

            List<Long> dogIds = getDogIdsFromDogsList(owner.getDogs());

            return ownerConverter.ownerToResponseDto(owner, dogIds);
        }
    }

    public void delete(Long ownerId) {
        Owner owner = ownRepository.findById(ownerId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found. Owner id: " + ownerId));

        ownRepository.delete(owner);
    }

    private List<Long> getDogIdsFromDogsList(List<Dog> dogsList) {
        return dogsList != null && !dogsList.isEmpty()
            ? dogsList
            .stream()
            .map(Dog::getId)
            .collect(Collectors.toList())
            : null;
    }

    private List<Dog> getDogsFromDogsDto(List<DogDto> dogDtos, Owner owner) {
        return dogDtos.stream()
            .map(dogDto -> {
                Breed breed = breedService.getBreedByIdOrThrow(dogDto.getBreedId());
                return dogConverter.mapDtoToDog(dogDto, owner, breed);
            })
            .toList();
    }

}
