package com.monkeybar.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class MenuItemRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @NotNull
    @Positive
    private Double price;

    private String description;
    private String imageUrl;
    private List<String> allergens;

    @NotNull
    private UUID venueId;
}
