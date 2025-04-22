package com.umbrellainsur.insurance.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "quotes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String address;
    private String locationCity;
    private String locationState;
    private String zipCode;
    private Double propertyArea;
    private Double coverage_limit;
    private String status;
    private Double finalPremium;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "broker_id")
    private Broker broker;

    @OneToOne(mappedBy = "quote", cascade = CascadeType.ALL)
    private InjuriesCoverage injuriesCoverage;

    @OneToOne(mappedBy = "quote", cascade = CascadeType.ALL)
    private PropertyCoverage propertyCoverage;

    @OneToOne(mappedBy = "quote", cascade = CascadeType.ALL)
    private LawsuitsCoverage lawsuitsCoverage;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}