package com.example.dogsproject.converter;

import com.example.dogsproject.dto.DogDto;
import com.example.dogsproject.models.Breed;
import com.example.dogsproject.models.Dog;
import com.example.dogsproject.models.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DogConverter {

    Dog mapDtoToDog(DogDto dogDto, Breed breed, Owner owner);

    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "breed.id", target = "breedId")
    DogDto dogToDto(Dog dog);


}
