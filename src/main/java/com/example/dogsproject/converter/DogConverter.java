package com.example.dogsproject.converter;

import com.example.dogsproject.dto.DogDto;
import com.example.dogsproject.models.Breed;
import com.example.dogsproject.models.Dog;
import com.example.dogsproject.models.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DogConverter {

    @Mapping(source = "dogDto.id", target = "id")
    @Mapping(source = "dogDto.name", target = "name")
    @Mapping(source = "dogDto.dateOfBirth", target = "dateOfBirth")
    Dog mapDtoToDog(DogDto dogDto, Owner owner, Breed breed);

    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "breed.id", target = "breedId")
    DogDto dogToDto(Dog dog);


}
