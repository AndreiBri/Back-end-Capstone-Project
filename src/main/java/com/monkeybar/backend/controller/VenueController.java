package com.monkeybar.backend.controller;

import com.monkeybar.backend.dto.request.VenueRequestDTO;
import com.monkeybar.backend.dto.response.VenueResponseDTO;
import com.monkeybar.backend.service.VenueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @GetMapping
    public ResponseEntity<List<VenueResponseDTO>> getAll() {
        return ResponseEntity.ok(venueService.getAll());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<VenueResponseDTO> getBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(venueService.getBySlug(slug));
    }

    @PostMapping
    public ResponseEntity<VenueResponseDTO> create(@Valid @RequestBody VenueRequestDTO dto) {
        return ResponseEntity.ok(venueService.create(dto));
    }
}