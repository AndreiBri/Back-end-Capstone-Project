package com.monkeybar.backend.repository;

import com.monkeybar.backend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByVenueId(UUID venueId);

    List<Booking> findByVenueIdAndArchivedFalse(UUID venueId);

    boolean existsByCustomerEmailAndVenueIdAndBookingDateBetween(
            String email, UUID venueId, LocalDate from, LocalDate to
    );
}
