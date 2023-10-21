package com.example.dogsproject.converter;

import com.example.dogsproject.dto.OwnerCreateDto;
import com.example.dogsproject.dto.OwnerResponseDto;
import com.example.dogsproject.models.Dog;
import com.example.dogsproject.models.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OwnerConverter {

    @Mapping(source = "dogs", target = "dogs")
    Owner createDtoToOwner(OwnerCreateDto dto, List<Dog> dogs);

    OwnerResponseDto ownerToResponseDto(Owner owner, List<Long> dogIds);

}
