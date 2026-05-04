package com.monkeybar.backend.service;

import com.monkeybar.backend.dto.request.VenueRequestDTO;
import com.monkeybar.backend.dto.response.VenueResponseDTO;
import com.monkeybar.backend.entity.Venue;
import com.monkeybar.backend.mapper.EntityMapper;
import com.monkeybar.backend.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    public List<VenueResponseDTO> getAll() {
        return venueRepository.findAll()
                .stream()
                .map(EntityMapper::toVenueResponse)
                .toList();
    }

    public VenueResponseDTO getBySlug(String slug) {
        return venueRepository.findBySlug(slug)
                .map(EntityMapper::toVenueResponse)
                .orElseThrow(() -> new RuntimeException("Venue non trovata"));
    }

    public Venue getEntityById(UUID id) {
        return venueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venue non trovata"));
    }

    public VenueResponseDTO create(VenueRequestDTO dto) {
        Venue venue = new Venue();
        venue.setName(dto.getName());
        venue.setSlug(dto.getSlug());
        return EntityMapper.toVenueResponse(venueRepository.save(venue));
    }
}