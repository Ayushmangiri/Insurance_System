package com.umbrellainsur.insurance.service;

import com.umbrellainsur.insurance.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PremiumCalculationService {

    public Quote calculatePremium(Quote quote) {
        double totalCoverage = 0.0;

        InjuriesCoverage injuries = quote.getInjuriesCoverage();
        PropertyCoverage property = quote.getPropertyCoverage();
        LawsuitsCoverage lawsuits = quote.getLawsuitsCoverage();

        if (injuries != null) {
            totalCoverage += defaultValue(injuries.getBodilyInjuryLimit());
            totalCoverage += defaultValue(injuries.getMedicalExpensesLimit());
        }

        if (property != null) {
            totalCoverage += defaultValue(property.getPropertyDamageLimit());
        }

        if (lawsuits != null) {
            totalCoverage += defaultValue(lawsuits.getPersonalLiabilityLimit());
        }

        double basePremium = 0.0;

        // A. Coverage-based
        if (injuries != null) {
            basePremium += defaultValue(injuries.getBodilyInjuryLimit()) * 0.01;
            basePremium += defaultValue(injuries.getMedicalExpensesLimit()) * 0.015;
        }

        if (property != null) {
            basePremium += defaultValue(property.getPropertyDamageLimit()) * 0.005;
        }

        if (lawsuits != null) {
            basePremium += defaultValue(lawsuits.getPersonalLiabilityLimit()) * 0.004;
        }

        // B. Risk-based
        if (Boolean.TRUE.equals(quote.getInjuriesCoverage().getHostsPublicEvents())) basePremium += totalCoverage * 0.01;
        if (Boolean.TRUE.equals(quote.getInjuriesCoverage().getHasDangerousStructures())) basePremium += totalCoverage * 0.008;
        if (Boolean.TRUE.equals(quote.getInjuriesCoverage().getHasDomesticWorkers())) basePremium += totalCoverage * 0.006;
        if (Boolean.FALSE.equals(quote.getPropertyCoverage().getHasFence())) basePremium += totalCoverage * 0.005;
        if (Boolean.FALSE.equals(quote.getPropertyCoverage().getHasAlarms())) basePremium += totalCoverage * 0.005;
        if (Boolean.TRUE.equals(quote.getPropertyCoverage().getCommercialUse())) basePremium += totalCoverage * 0.01;
        if (Boolean.TRUE.equals(quote.getPropertyCoverage().getHadClaims())) basePremium += totalCoverage * 0.006;
        if (Boolean.TRUE.equals(quote.getLawsuitsCoverage().getContentCreator())) basePremium += totalCoverage * 0.005;
        if (Boolean.TRUE.equals(quote.getLawsuitsCoverage().getOwnsWeapons())) basePremium += totalCoverage * 0.007;
        if (Boolean.TRUE.equals(quote.getLawsuitsCoverage().getOwnsHighRiskPets())) basePremium += totalCoverage * 0.006;
        if (Boolean.TRUE.equals(quote.getLawsuitsCoverage().getHadPriorLawsuits())) basePremium += totalCoverage * 0.01;
        if (Boolean.TRUE.equals(quote.getLawsuitsCoverage().getLegalDefenseRequired())) basePremium += totalCoverage * 0.012;

        // C. Count-based
        basePremium += (defaultInt(quote.getInjuriesCoverage().getNumberOfGuests()) * 0.001) * totalCoverage;
        basePremium += (defaultInt(quote.getInjuriesCoverage().getNumberOfHouseholdMembers()) * 0.0005) * totalCoverage;
        basePremium += (defaultInt(quote.getPropertyCoverage().getNumberOfProperties()) * 0.002) * totalCoverage;

        // D. Cap at 5%
        double maxAllowedPremium = totalCoverage * 0.05;
        double finalPremium = Math.min(basePremium, maxAllowedPremium);

        quote.setFinalPremium(finalPremium);
        quote.setStatus("CALCULATED");
        quote.setCreatedAt(LocalDateTime.now());
        quote.setUpdatedAt(LocalDateTime.now());

        return quote;
    }

    private double defaultValue(Double value) {
        return value != null ? value : 0.0;
    }

    private int defaultInt(Integer value) {
        return value != null ? value : 0;
    }
}
