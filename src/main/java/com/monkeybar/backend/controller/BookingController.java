package com.monkeybar.backend.controller;

import com.monkeybar.backend.dto.request.BookingRequestDTO;
import com.monkeybar.backend.dto.response.BookingResponseDTO;
import com.monkeybar.backend.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/{venueId}")
    public ResponseEntity<List<BookingResponseDTO>> getActiveByVenueId(@PathVariable UUID venueId) {
        return ResponseEntity.ok(bookingService.getActiveByVenueId(venueId));
    }

    @PostMapping
    public ResponseEntity<BookingResponseDTO> create(@Valid @RequestBody BookingRequestDTO dto) {
        return ResponseEntity.ok(bookingService.create(dto));
    }

    @PatchMapping("/{id}/archive")
    public ResponseEntity<BookingResponseDTO> archive(@PathVariable UUID id) {
        return ResponseEntity.ok(bookingService.archive(id));
    }
}