package com.monkeybar.backend.dto.response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class MenuItemResponseDTO {
    private UUID id;
    private String name;
    private String category;
    private Double price;
    private String imageUrl;
    private String description;
    private boolean visible;
    private List<String> allergens;
    private UUID venueId;
    private String venueName;
}