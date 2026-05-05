package com.monkeybar.backend.service;

import com.monkeybar.backend.dto.request.LoginRequestDTO;
import com.monkeybar.backend.dto.request.RegisterRequestDTO;
import com.monkeybar.backend.dto.response.AuthResponseDTO;
import com.monkeybar.backend.entity.Profile;
import com.monkeybar.backend.entity.Venue;
import com.monkeybar.backend.enums.Role;
import com.monkeybar.backend.repository.ProfileRepository;
import com.monkeybar.backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ProfileRepository profileRepository;
    private final VenueService venueService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthResponseDTO register(RegisterRequestDTO dto) {
        if (profileRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email già registrata.");
        }

        Profile profile = new Profile();
        profile.setEmail(dto.getEmail());
        profile.setPassword(passwordEncoder.encode(dto.getPassword()));
        profile.setRole(dto.getRole());

        if (dto.getRole() == Role.OWNER) {
            // OWNER non ha venue
            profile.setVenue(null);
        } else {
            // SUPERVISOR e STAFF devono avere uno slug venue
            if (dto.getVenueSlug() == null) {
                throw new RuntimeException("SUPERVISOR e STAFF devono essere assegnati a una venue.");
            }
            Venue venue = venueService.getEntityBySlug(dto.getVenueSlug().toString());
            profile.setVenue(venue);
        }

        profileRepository.save(profile);
        return buildAuthResponse(profile);
    }

    public AuthResponseDTO login(LoginRequestDTO dto) {
        Profile profile = profileRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenziali non valide."));

        if (!passwordEncoder.matches(dto.getPassword(), profile.getPassword())) {
            throw new RuntimeException("Credenziali non valide.");
        }

        return buildAuthResponse(profile);
    }

    private AuthResponseDTO buildAuthResponse(Profile profile) {
        String token = jwtUtil.generateToken(profile.getEmail(), profile.getRole().name());

        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);
        response.setProfileId(profile.getId());
        response.setEmail(profile.getEmail());
        response.setRole(profile.getRole());

        if (profile.getVenue() != null) {
            response.setVenueId(profile.getVenue().getId());
            response.setVenueName(profile.getVenue().getName());
        }

        return response;
    }
}