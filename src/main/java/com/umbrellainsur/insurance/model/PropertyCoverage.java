package com.umbrellainsur.insurance.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "property_coverage")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyCoverage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double propertyDamageLimit;
    private Boolean hasFence;
    private Boolean hasAlarms;
    private Boolean hadClaims;
    private Boolean commercialUse;
    private Integer numberOfProperties;

    @OneToOne
    @JoinColumn(name = "quote_id", nullable = false)
    private Quote quote;
}