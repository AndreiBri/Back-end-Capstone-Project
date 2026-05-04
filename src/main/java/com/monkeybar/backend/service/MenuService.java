package com.monkeybar.backend.service;

import com.monkeybar.backend.dto.request.MenuItemRequestDTO;
import com.monkeybar.backend.dto.response.MenuItemResponseDTO;
import com.monkeybar.backend.entity.MenuItem;
import com.monkeybar.backend.entity.Venue;
import com.monkeybar.backend.mapper.EntityMapper;
import com.monkeybar.backend.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuItemRepository menuItemRepository;
    private final VenueService venueService;

    public List<MenuItemResponseDTO> getPublicMenu(UUID venueId) {
        return menuItemRepository.findByVenueIdAndVisibleTrue(venueId)
                .stream()
                .map(EntityMapper::toMenuItemResponse)
                .toList();
    }

    public List<MenuItemResponseDTO> getFullMenu(UUID venueId) {
        return menuItemRepository.findByVenueId(venueId)
                .stream()
                .map(EntityMapper::toMenuItemResponse)
                .toList();
    }

    public MenuItemResponseDTO getById(UUID id) {
        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato"));
        return EntityMapper.toMenuItemResponse(item);
    }

    public MenuItemResponseDTO create(MenuItemRequestDTO dto) {
        Venue venue = venueService.getEntityById(dto.getVenueId());

        MenuItem item = new MenuItem();
        item.setName(dto.getName());
        item.setCategory(dto.getCategory());
        item.setPrice(dto.getPrice());
        item.setDescription(dto.getDescription());
        item.setAllergens(dto.getAllergens());
        item.setImageUrl(dto.getImageUrl());
        item.setVisible(true);
        item.setVenue(venue);

        return EntityMapper.toMenuItemResponse(menuItemRepository.save(item));
    }

    public MenuItemResponseDTO toggleVisibility(UUID id) {
        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato"));
        item.setVisible(!item.isVisible());
        return EntityMapper.toMenuItemResponse(menuItemRepository.save(item));
    }

    public void delete(UUID id) {
        if (!menuItemRepository.existsById(id)) {
            throw new RuntimeException("Prodotto non trovato");
        }
        menuItemRepository.deleteById(id);
    }
}