package com.umbrellainsur.insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuoteResponseDTO {
    private Double finalPremium;
    private Double maxAllowedPremium;
    private String message;
}