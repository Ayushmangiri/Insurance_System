package com.umbrellainsur.insurance.dto;

import lombok.Data;

@Data
public class QuoteRequestDTO {
    private Long brokerId;
    private String customerName;
    private Double bodilyInjuryLimit;
    private Double medicalExpensesLimit;
    private Integer numberOfGuests;
    private Integer numberOfHouseholdMembers;
    private Double propertyDamageLimit;
    private Boolean hasFence;
    private Boolean hasAlarms;
    private Boolean hadClaims;
    private Integer numberOfProperties;
    private Double personalLiabilityLimit;
    private Boolean ownsWeapons;
    private Boolean ownsHighRiskPets;
    private Boolean hadPriorLawsuits;
    private Boolean legalDefenseRequired;
    private Boolean commercialUse;
    private Boolean hostsPublicEvents;
    private Boolean hasDangerousStructures;
    private Boolean hasDomesticWorkers;
    private Boolean contentCreator;

    private String address;
    private String locationCity;
    private String locationState;
    private String zipCode;
    private Double propertyArea;
}