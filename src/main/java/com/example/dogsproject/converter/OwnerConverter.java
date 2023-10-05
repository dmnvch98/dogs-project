package com.example.dogsproject.converter;

import com.example.dogsproject.dto.DogDto;
import com.example.dogsproject.dto.OwnerCreateDto;
import com.example.dogsproject.dto.OwnerResponseDto;
import com.example.dogsproject.models.Dog;
import com.example.dogsproject.models.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
@Component
public abstract class OwnerConverter {
    @Autowired
    private DogConverter dogConverter;

    @Mapping(source = "dogs", target = "dogs", qualifiedByName = "mapDogsDtoToDogs")
    public abstract Owner createDtoToOwner(OwnerCreateDto dto);

    @Mapping(source = "dogs", target = "dogIds", qualifiedByName = "extractDogIdsFromDogsList")
    public abstract OwnerResponseDto ownerToResponseDto(Owner owner);

    @Named("extractDogIdsFromDogsList")
    protected List<Long> extractDogIdsFromDogsList(List<Dog> dogsList) {
        return dogsList != null && !dogsList.isEmpty()
                ? dogsList
                .stream()
                .map(Dog::getId)
                .collect(Collectors.toList())
                : null;
    }

    @Named("mapDogsDtoToDogs")
    protected List<Dog> mapDogsDtoToDogs(List<DogDto> dogsList) {
        return dogsList != null && !dogsList.isEmpty() ? dogsList
                .stream()
                .map(dogConverter::mapDtoToDog)
                .collect(Collectors.toList())
                : null;
    }

}
