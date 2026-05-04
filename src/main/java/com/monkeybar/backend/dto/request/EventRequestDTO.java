package com.monkeybar.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class EventRequestDTO {

    @NotBlank
    private String title;

    private String description;
    private String imageUrl;

    @NotNull
    private LocalDate eventDate;

    @NotNull
    private UUID venueId;
}