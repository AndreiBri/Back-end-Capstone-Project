package com.monkeybar.backend.controller;

import com.monkeybar.backend.dto.request.EventRequestDTO;
import com.monkeybar.backend.dto.response.EventResponseDTO;
import com.monkeybar.backend.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final ObjectMapper objectMapper;

    @GetMapping("/{venueId}")
    public ResponseEntity<List<EventResponseDTO>> getByVenueId(@PathVariable UUID venueId) {
        return ResponseEntity.ok(eventService.getByVenueId(venueId));
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<EventResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(eventService.getById(id));
    }

    @PostMapping
    public ResponseEntity<EventResponseDTO> create(@Valid @RequestBody EventRequestDTO dto) throws IOException {
        return ResponseEntity.ok(eventService.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}