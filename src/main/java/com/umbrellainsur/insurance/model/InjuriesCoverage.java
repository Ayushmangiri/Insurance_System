package com.umbrellainsur.insurance.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "injuries_coverage")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InjuriesCoverage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double bodilyInjuryLimit;
    private Double medicalExpensesLimit;
    private Integer numberOfGuests;
    private Integer numberOfHouseholdMembers;
    private Boolean hostsPublicEvents;
    private Boolean hasDangerousStructures;
    private Boolean hasDomesticWorkers;


    @OneToOne
    @JoinColumn(name = "quote_id", nullable = false)
    private Quote quote;
}