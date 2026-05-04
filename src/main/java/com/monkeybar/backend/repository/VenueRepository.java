package com.monkeybar.backend.repository;

import com.monkeybar.backend.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VenueRepository extends JpaRepository<Venue, UUID> {
    Optional<Venue> findBySlug(String slug);
}
