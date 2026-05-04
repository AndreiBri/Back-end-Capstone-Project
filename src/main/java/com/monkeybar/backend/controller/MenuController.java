package com.monkeybar.backend.controller;

import com.monkeybar.backend.dto.request.MenuItemRequestDTO;
import com.monkeybar.backend.dto.response.MenuItemResponseDTO;
import com.monkeybar.backend.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/{venueId}")
    public ResponseEntity<List<MenuItemResponseDTO>> getPublicMenu(@PathVariable UUID venueId) {
        return ResponseEntity.ok(menuService.getPublicMenu(venueId));
    }

    @GetMapping("/{venueId}/all")
    public ResponseEntity<List<MenuItemResponseDTO>> getFullMenu(@PathVariable UUID venueId) {
        return ResponseEntity.ok(menuService.getFullMenu(venueId));
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<MenuItemResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(menuService.getById(id));
    }

    @PostMapping
    public ResponseEntity<MenuItemResponseDTO> create(@Valid @RequestBody MenuItemRequestDTO dto) {
        return ResponseEntity.ok(menuService.create(dto));
    }

    @PatchMapping("/{id}/visibility")
    public ResponseEntity<MenuItemResponseDTO> toggleVisibility(@PathVariable UUID id) {
        return ResponseEntity.ok(menuService.toggleVisibility(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        menuService.delete(id);
        return ResponseEntity.noContent().build();
    }
}