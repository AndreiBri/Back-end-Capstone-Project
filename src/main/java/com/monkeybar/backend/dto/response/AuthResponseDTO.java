package com.monkeybar.backend.dto.response;

import com.monkeybar.backend.enums.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class AuthResponseDTO {
    private String token;
    private UUID profileId;
    private String email;
    private Role role;
    private UUID venueId;
    private String venueName;
}