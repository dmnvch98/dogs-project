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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BreedService {
    private final BreedGroupRepository breedGroupRepository;
    private final ApiSender apiSender;
    private final BreedRepository breedRepository;

    @Value("${remote.breed_url}")
    public String GET_BREEDS_URL;

    @PostConstruct
    private void fetchBreedsFromRemoteRepositoryAndSave() {
        log.info("Getting breeds from remote repository");
        BreedDto breedDto = getBreedsFromRemoteRepository();

        List<BreedGroup> breedGroupList = parseBreedGroupsFromBreedDto(breedDto);

        log.info("Breeds were fetched and parsed. The breedGroupList size is {}", breedGroupList.size());
        saveAll(breedGroupList);
    }

    @Transactional
    void saveAll(List<BreedGroup> breedGroupList) {
        breedGroupRepository.saveAll(breedGroupList);
    }

    protected BreedDto getBreedsFromRemoteRepository() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "/");
        return apiSender.get(GET_BREEDS_URL, headers, new GenericType<>() {
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
                    .collect(Collectors.toList())
                );
                return breedGroup;
            })
            .collect(Collectors.toList());
    }

    public Breed getBreedByIdOrThrow(Long id) {
        return breedRepository
            .findById(id)
            .orElseThrow(() -> new AppException( "Breed not found. Breed id: " + id, HttpStatus.NOT_FOUND));
    }

}
