package com.umbrellainsur.insurance.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "quote_ratings")
@Data
@NoArgsConstructor
public class QuoteRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "quote_id", nullable = false)
    private Quote quote;

    @Column(name = "base_premium", nullable = false)
    private Double basePremium;

    @Column(name = "max_premium", nullable = false)
    private Double maxPremium;

    @Column(name = "rating_notes")
    private String ratingNotes;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}