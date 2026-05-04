package com.monkeybar.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VenueRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String slug;
}
