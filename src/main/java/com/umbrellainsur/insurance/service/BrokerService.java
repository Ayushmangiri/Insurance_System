package com.umbrellainsur.insurance.service;

import com.umbrellainsur.insurance.model.Broker;
import com.umbrellainsur.insurance.model.Quote;
import com.umbrellainsur.insurance.repository.BrokerRepository;
import com.umbrellainsur.insurance.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrokerService {

    private final BrokerRepository brokerRepository;
    private final QuoteRepository quoteRepository;

    public List<Quote> getQuotesByBroker(Long brokerId) {
        return quoteRepository.findByBrokerId(brokerId);
    }

    public Quote softDeleteQuote(Long quoteId) {
        return quoteRepository.findById(quoteId).map(quote -> {
            quote.setStatus("DELETED");
            return quoteRepository.save(quote);
        }).orElseThrow(() -> new RuntimeException("Quote not found"));
    }

    public Quote submitQuote(Long quoteId) {
        return quoteRepository.findById(quoteId).map(quote -> {
            quote.setStatus("SUBMITTED");
            return quoteRepository.save(quote);
        }).orElseThrow(() -> new RuntimeException("Quote not found"));
    }

    public Quote editQuote(Long quoteId, Quote updated) {
        return quoteRepository.findById(quoteId).map(existing -> {
            updated.setId(existing.getId());
            return quoteRepository.save(updated);
        }).orElseThrow(() -> new RuntimeException("Quote not found"));
    }
    public Broker saveBroker(Broker broker) {
        if (brokerRepository.findByEmail(broker.getEmail()).isPresent()) {
            throw new RuntimeException("Broker with this email already exists");
        }
        return brokerRepository.save(broker);
    }



    public Broker getBrokerDetails(Long brokerId) {
        return brokerRepository.findById(brokerId).orElseThrow(() -> new RuntimeException("Broker not found"));
    }
}