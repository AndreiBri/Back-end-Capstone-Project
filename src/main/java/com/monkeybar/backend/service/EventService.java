package com.monkeybar.backend.service;

import com.monkeybar.backend.dto.request.EventRequestDTO;
import com.monkeybar.backend.dto.response.EventResponseDTO;
import com.monkeybar.backend.entity.Event;
import com.monkeybar.backend.entity.Venue;
import com.monkeybar.backend.mapper.EntityMapper;
import com.monkeybar.backend.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final VenueService venueService;

    public List<EventResponseDTO> getByVenueId(UUID venueId) {
        return eventRepository.findByVenueId(venueId)
                .stream()
                .map(EntityMapper::toEventResponse)
                .toList();
    }

    public EventResponseDTO getById(UUID id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento non trovato"));
        return EntityMapper.toEventResponse(event);
    }

    public EventResponseDTO create(EventRequestDTO dto) throws IOException {
        Venue venue = venueService.getEntityById(dto.getVenueId());

        Event event = new Event();
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setEventDate(dto.getEventDate());
        event.setVenue(venue);

        return EntityMapper.toEventResponse(eventRepository.save(event));
    }

    public void delete(UUID id) {
        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Evento non trovato");
        }
        eventRepository.deleteById(id);
    }
}