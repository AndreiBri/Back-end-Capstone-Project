package com.monkeybar.backend.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EventResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private String imageUrl;
    private LocalDateTime eventDate;
    private UUID venueId;
    private String venueName;
}