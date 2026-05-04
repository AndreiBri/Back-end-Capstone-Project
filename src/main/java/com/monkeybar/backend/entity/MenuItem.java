package com.monkeybar.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "menu_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String category;
    private Double price;
    private String imageUrl;
    private String description;
    private boolean visible = true;

    @ElementCollection
    @CollectionTable(name = "menu_item_allergens", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "allergen")
    private List<String> allergens;

    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;
}
