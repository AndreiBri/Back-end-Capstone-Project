package com.monkeybar.backend.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookingRequestDTO {
    @NotBlank
    private String customerName;

    @NotBlank
    @Email
    private String customerEmail;

    private String customerPhone;

    @NotNull
    @Future
    private LocalDateTime bookingDate;

    @NotNull
    @Min(1)
    @Max(20)
    private Integer guests;

    @NotNull
    private UUID venueId;
}
