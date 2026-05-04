package com.monkeybar.backend.service;

import com.monkeybar.backend.dto.request.BookingRequestDTO;
import com.monkeybar.backend.dto.response.BookingResponseDTO;
import com.monkeybar.backend.entity.Booking;
import com.monkeybar.backend.entity.Venue;
import com.monkeybar.backend.mapper.EntityMapper;
import com.monkeybar.backend.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final VenueService venueService;

    public List<BookingResponseDTO> getActiveByVenueId(UUID venueId) {
        return bookingRepository.findByVenueIdAndArchivedFalse(venueId)
                .stream()
                .map(EntityMapper::toBookingResponse)
                .toList();
    }

    public BookingResponseDTO create(BookingRequestDTO dto) {
        LocalDate from = LocalDate.from(dto.getBookingDate().minusHours(1));
        LocalDateTime to = dto.getBookingDate().plusHours(1);

        boolean duplicate = bookingRepository.existsByCustomerEmailAndVenueIdAndBookingDateBetween(
                dto.getCustomerEmail(), dto.getVenueId(), from, LocalDate.from(to)
        );

        if (duplicate) {
            throw new RuntimeException("Hai già una prenotazione in questa fascia oraria.");
        }

        Venue venue = venueService.getEntityById(dto.getVenueId());

        Booking booking = new Booking();
        booking.setCustomerName(dto.getCustomerName());
        booking.setCustomerEmail(dto.getCustomerEmail());
        booking.setCustomerPhone(dto.getCustomerPhone());
        booking.setBookingDate(LocalDate.from(dto.getBookingDate()));
        booking.setGuests(dto.getGuests());
        booking.setVenue(venue);

        return EntityMapper.toBookingResponse(bookingRepository.save(booking));
    }

    public BookingResponseDTO archive(UUID id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));
        booking.setArchived(true);
        return EntityMapper.toBookingResponse(bookingRepository.save(booking));
    }
}