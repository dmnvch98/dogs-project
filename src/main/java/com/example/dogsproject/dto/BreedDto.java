package com.example.dogsproject.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Builder
@Data
public class BreedDto {
    private Map<String, List<String>> message;
}
