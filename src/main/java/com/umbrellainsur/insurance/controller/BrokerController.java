package com.umbrellainsur.insurance.controller;

import com.umbrellainsur.insurance.model.Broker;
import com.umbrellainsur.insurance.model.Quote;
import com.umbrellainsur.insurance.service.BrokerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/brokers")
@RequiredArgsConstructor
public class BrokerController{
@PostMapping
public ResponseEntity<?> createBroker(@RequestBody Broker broker) {
    try {
        Broker savedBroker = brokerService.saveBroker(broker);
        return ResponseEntity.ok(savedBroker);
    } catch (RuntimeException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }
}


private final BrokerService brokerService;

    // Get Quotes by Broker ID
    @GetMapping("/{brokerId}/quotes")
    public ResponseEntity<List<Quote>> getQuotesByBroker(@PathVariable Long brokerId) {
        List<Quote> quotes = brokerService.getQuotesByBroker(brokerId);
        if (quotes.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 if no quotes found
        }
        return ResponseEntity.ok(quotes);
    }

    //profile for

    @GetMapping("/profile")
    public ResponseEntity<?> getBrokerProfile(Authentication authentication) {
        Broker broker = (Broker) authentication.getPrincipal();

        Map<String, Object> response = Map.of(
                "id", broker.getId(),
                "name", broker.getName(),
                "email", broker.getEmail()
        );

        return ResponseEntity.ok(response);
    }

    // Soft Delete Quote by Quote ID
    @PutMapping("/quotes/{quoteId}/soft-delete")
    public ResponseEntity<Quote> softDeleteQuote(@PathVariable Long quoteId) {
        try {
            Quote deletedQuote = brokerService.softDeleteQuote(quoteId);
            return ResponseEntity.ok(deletedQuote); // Return deleted quote
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null); // Return 404 if quote not found
        }
    }

    // Submit Quote by Quote ID
    @PutMapping("/quotes/{quoteId}/submit")
    public ResponseEntity<Quote> submitQuote(@PathVariable Long quoteId) {
        try {
            Quote submittedQuote = brokerService.submitQuote(quoteId);
            return ResponseEntity.ok(submittedQuote); // Return submitted quote
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null); // Return 404 if quote not found
        }
    }

    // Edit Quote by Quote ID
    @PutMapping("/quotes/{quoteId}/edit")
    public ResponseEntity<Quote> editQuote(@PathVariable Long quoteId, @RequestBody Quote updatedQuote) {
        try {
            Quote updated = brokerService.editQuote(quoteId, updatedQuote);
            return ResponseEntity.ok(updated); // Return updated quote
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null); // Return 404 if quote not found
        }
    }

    // Get Broker Details by Broker ID
    @GetMapping("/{brokerId}")
    public ResponseEntity<Broker> getBrokerDetails(@PathVariable Long brokerId) {
        try {
            Broker broker = brokerService.getBrokerDetails(brokerId);
            if (broker == null) {
                return ResponseEntity.status(404).body(null);
            }
            return ResponseEntity.ok(broker);
        } catch (Exception e) {
            e.printStackTrace(); // Logs error in console
            return ResponseEntity.status(500).body(null);
        }
    }



}
