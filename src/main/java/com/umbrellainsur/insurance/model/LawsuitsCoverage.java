package com.umbrellainsur.insurance.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lawsuits_coverage")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LawsuitsCoverage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double personalLiabilityLimit;
    private Boolean contentCreator;
    private Boolean ownsWeapons;
    private Boolean ownsHighRiskPets;
    private Boolean hadPriorLawsuits;
    private Boolean legalDefenseRequired;

    @OneToOne
    @JoinColumn(name = "quote_id", nullable = false)
    private Quote quote;
}