package com.umbrellainsur.insurance.controller;

import com.umbrellainsur.insurance.model.Quote;
import com.umbrellainsur.insurance.service.PremiumCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/premium")
@RequiredArgsConstructor
public class PremiumCalculationController {

    private final PremiumCalculationService premiumCalculationService;

    @PostMapping("/calculate")
    public ResponseEntity<Quote> calculatePremium(@RequestBody Quote quote) {
        return ResponseEntity.ok(premiumCalculationService.calculatePremium(quote));
    }
}
