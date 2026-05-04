package com.monkeybar.backend.repository;

import com.monkeybar.backend.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {
    List<MenuItem> findByVenueId(UUID venueId);

    List<MenuItem> findByVenueIdAndVisibleTrue(UUID venueId);
}
