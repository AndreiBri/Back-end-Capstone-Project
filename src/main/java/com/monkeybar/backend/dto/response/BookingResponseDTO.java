package com.monkeybar.backend.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookingResponseDTO {
    private UUID id;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private LocalDateTime bookingDate;
    private Integer guests;
    private boolean archived;
    private UUID venueId;
    private String venueName;
}