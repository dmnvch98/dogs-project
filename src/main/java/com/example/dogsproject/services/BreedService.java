package com.example.dogsproject.services;

import com.example.dogsproject.apisender.ApiSender;
import com.example.dogsproject.dto.BreedDto;
import com.example.dogsproject.exceptions.AppException;
import com.example.dogsproject.models.Breed;
import com.example.dogsproject.models.BreedGroup;
import com.example.dogsproject.repositories.BreedGroupRepository;
import com.example.dogsproject.repositories.BreedRepository;
import jakarta.annotation.PostConstruct;
import kong.unirest.GenericType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BreedService {
    private final BreedGroupRepository breedGroupRepository;
    private final ApiSender apiSender;
    private final BreedRepository breedRepository;

    @PostConstruct
    private void fetchBreedsFromRemoteRepositoryAndSave() {
        BreedDto breedDto = getBreedsFromRemoteRepository();

        List<BreedGroup> breedGroupList = parseBreedGroupsFromBreedDto(breedDto);

        breedGroupRepository.saveAll(breedGroupList);
    }

    protected BreedDto getBreedsFromRemoteRepository() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "/");
        return apiSender.get("https://dog.ceo/api/breeds/list/all", headers, new GenericType<>() {
        });
    }

    protected List<BreedGroup> parseBreedGroupsFromBreedDto(BreedDto breedDto) {
        return breedDto
            .getMessage()
            .entrySet()
            .stream()
            .map(entry -> {
                BreedGroup breedGroup = new BreedGroup(entry.getKey());
                breedGroup.setSubBreeds(entry
                    .getValue()
                    .stream()
                    .map(breedName -> {
                        Breed breed = new Breed(breedName);
                        breed.setBreedGroup(breedGroup);
                        return breed;
                    })
                    .toList()
                );
                return breedGroup;
            })
            .toList();
    }

    public Breed getBreedByIdOrThrow(Long id) {
        return breedRepository
            .findById(id)
            .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Breed not found"));
    }

}
