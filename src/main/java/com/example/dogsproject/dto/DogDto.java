package com.example.dogsproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

@Data
@Builder
@Jacksonized
public class DogDto {
    private String name;
    private Long ownerId;
    private Long breedId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth ;
    private int weight;
    private int height;
}
