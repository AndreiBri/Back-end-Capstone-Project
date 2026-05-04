package com.monkeybar.backend.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class VenueResponseDTO {
    private UUID id;
    private String name;
    private String slug;
}