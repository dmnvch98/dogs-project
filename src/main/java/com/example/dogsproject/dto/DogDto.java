package com.example.dogsproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Data
@Builder
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class DogDto {
    private Long id;
    private String name;
    private Long ownerId;
    private Long breedId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth ;
    private Integer weight;
    private Integer height;
}
