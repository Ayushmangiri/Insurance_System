package com.umbrellainsur.insurance.mapper;

import com.umbrellainsur.insurance.dto.QuoteRequestDTO;
import com.umbrellainsur.insurance.dto.QuoteResponseDTO;
import com.umbrellainsur.insurance.model.*;

import java.time.LocalDateTime;

public class QuoteMapper {

    public static Quote toEntity(QuoteRequestDTO dto, Broker broker) {
        Quote quote = new Quote();
        quote.setCustomerName(dto.getCustomerName());
        quote.setAddress(dto.getAddress());
        quote.setLocationCity(dto.getLocationCity());
        quote.setLocationState(dto.getLocationState());
        quote.setZipCode(dto.getZipCode());
        quote.setPropertyArea(dto.getPropertyArea());
        quote.setStatus("DRAFT");
        quote.setBroker(broker);
        quote.setCreatedAt(LocalDateTime.now());
        quote.setUpdatedAt(LocalDateTime.now());

        // InjuriesCoverage
        InjuriesCoverage injuries = new InjuriesCoverage();
        injuries.setBodilyInjuryLimit(dto.getBodilyInjuryLimit());
        injuries.setMedicalExpensesLimit(dto.getMedicalExpensesLimit());
        injuries.setNumberOfGuests(dto.getNumberOfGuests());
        injuries.setNumberOfHouseholdMembers(dto.getNumberOfHouseholdMembers());
        injuries.setHostsPublicEvents(dto.getHostsPublicEvents());
        injuries.setHasDangerousStructures(dto.getHasDangerousStructures());
        injuries.setHasDomesticWorkers(dto.getHasDomesticWorkers());
        injuries.setQuote(quote);
        quote.setInjuriesCoverage(injuries);

        // PropertyCoverage
        PropertyCoverage property = new PropertyCoverage();
        property.setPropertyDamageLimit(dto.getPropertyDamageLimit());
        property.setHasFence(dto.getHasFence());
        property.setHasAlarms(dto.getHasAlarms());
        property.setHadClaims(dto.getHadClaims());
        property.setCommercialUse(dto.getCommercialUse());
        property.setNumberOfProperties(dto.getNumberOfProperties());
        property.setQuote(quote);
        quote.setPropertyCoverage(property);

        // LawsuitsCoverage
        LawsuitsCoverage lawsuits = new LawsuitsCoverage();
        lawsuits.setPersonalLiabilityLimit(dto.getPersonalLiabilityLimit());
        lawsuits.setContentCreator(dto.getContentCreator());
        lawsuits.setOwnsWeapons(dto.getOwnsWeapons());
        lawsuits.setOwnsHighRiskPets(dto.getOwnsHighRiskPets());
        lawsuits.setHadPriorLawsuits(dto.getHadPriorLawsuits());
        lawsuits.setLegalDefenseRequired(dto.getLegalDefenseRequired());
        lawsuits.setQuote(quote);
        quote.setLawsuitsCoverage(lawsuits);

        return quote;
    }

    public static QuoteResponseDTO toResponseDTO(Quote quote, QuoteRating rating, String message) {
        return new QuoteResponseDTO(
                quote.getFinalPremium(),
                rating.getMaxPremium(),
                message
        );
    }
}