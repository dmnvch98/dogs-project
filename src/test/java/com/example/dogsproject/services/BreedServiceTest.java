package com.example.dogsproject.services;

import com.example.dogsproject.dto.BreedDto;
import com.example.dogsproject.models.BreedGroup;
import com.example.dogsproject.repositories.BreedGroupRepository;
import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@Disabled
class BreedServiceTest {

//    @Autowired
    private BreedService breedService;
//    @Autowired
    private BreedGroupRepository breedGroupRepository;

    @Test
    @Disabled
    void getBreeds_Parse_And_Save() {
        BreedDto breedDto = breedService.getBreedsFromRemoteRepository();

        List<BreedGroup> breedGroupList = breedService.parseBreedGroupsFromBreedDto(breedDto);

        breedGroupRepository.saveAll(breedGroupList);

    }
}