package com.example.dogsproject.converter;

import com.example.dogsproject.dto.DogDto;
import com.example.dogsproject.models.Breed;
import com.example.dogsproject.models.Dog;
import com.example.dogsproject.models.Owner;
import com.example.dogsproject.services.BreedService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper
@Component
public abstract class DogConverter {
    @Autowired
    private BreedService breedService;

    @Mapping(source = "breedId", target = "breed", qualifiedByName = "getBreedById")
    public abstract Dog mapCreateDtoToDog(DogDto dogDto);

    @Mapping(source = "owner", target = "ownerId", qualifiedByName = "getOwnerId")
    @Mapping(source = "breed", target = "breedId", qualifiedByName = "getBreedId")
    public abstract DogDto dogToResponseDto(Dog dog);

    @Named("getBreedById")
    protected Breed getBreedById(Long id) {
        return id != null
            ? breedService.getBreedByIdOrThrow(id)
            : null;
    }

    @Named("getOwnerId")
    protected Long getOwnerId(Owner owner) {
        return owner != null
            ? owner.getId()
            : null;
    }

    @Named("getBreedId")
    protected Long getBreedId(Breed breed) {
        return breed != null
            ? breed.getId()
            : null;
    }

}
