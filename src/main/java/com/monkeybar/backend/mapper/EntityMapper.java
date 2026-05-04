package com.monkeybar.backend.mapper;

import com.monkeybar.backend.dto.response.*;
import com.monkeybar.backend.entity.*;

public class EntityMapper {

    public static VenueResponseDTO toVenueResponse(Venue v) {
        VenueResponseDTO dto = new VenueResponseDTO();
        dto.setId(v.getId());
        dto.setName(v.getName());
        dto.setSlug(v.getSlug());
        return dto;
    }

    public static MenuItemResponseDTO toMenuItemResponse(MenuItem item) {
        MenuItemResponseDTO dto = new MenuItemResponseDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setCategory(item.getCategory());
        dto.setPrice(item.getPrice());
        dto.setImageUrl(item.getImageUrl());
        dto.setDescription(item.getDescription());
        dto.setVisible(item.isVisible());
        dto.setAllergens(item.getAllergens());
        dto.setVenueId(item.getVenue().getId());
        dto.setVenueName(item.getVenue().getName());
        return dto;
    }

    public static EventResponseDTO toEventResponse(Event e) {
        EventResponseDTO dto = new EventResponseDTO();
        dto.setId(e.getId());
        dto.setTitle(e.getTitle());
        dto.setDescription(e.getDescription());
        dto.setImageUrl(e.getImageUrl());
        dto.setEventDate(e.getEventDate().atStartOfDay());
        dto.setVenueId(e.getVenue().getId());
        dto.setVenueName(e.getVenue().getName());
        return dto;
    }

    public static BookingResponseDTO toBookingResponse(Booking b) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(b.getId());
        dto.setCustomerName(b.getCustomerName());
        dto.setCustomerEmail(b.getCustomerEmail());
        dto.setCustomerPhone(b.getCustomerPhone());
        dto.setBookingDate(b.getBookingDate().atStartOfDay());
        dto.setGuests(b.getGuests());
        dto.setArchived(b.isArchived());
        dto.setVenueId(b.getVenue().getId());
        dto.setVenueName(b.getVenue().getName());
        return dto;
    }

    public static ProfileResponseDTO toProfileResponse(Profile p) {
        ProfileResponseDTO dto = new ProfileResponseDTO();
        dto.setId(p.getId());
        dto.setEmail(p.getEmail());
        dto.setRole(p.getRole());
        if (p.getVenue() != null) {
            dto.setVenueId(p.getVenue().getId());
            dto.setVenueName(p.getVenue().getName());
        }
        return dto;
    }
}