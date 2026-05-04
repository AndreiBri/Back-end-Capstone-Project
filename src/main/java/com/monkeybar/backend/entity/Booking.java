package com.monkeybar.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerEmail;

    private String customerPhone;

    @Column(nullable = false)
    private LocalDate bookingDate;

    @Column(nullable = false)
    private Integer guests;

    private boolean archived = false;

    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;


}
