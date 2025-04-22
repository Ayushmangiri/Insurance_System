package com.umbrellainsur.insurance.service;

import com.umbrellainsur.insurance.model.Quote;
import com.umbrellainsur.insurance.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteRepository quoteRepository;

    public Quote createQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    public Optional<Quote> getQuoteById(Long id) {
        return quoteRepository.findById(id);
    }

    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    public Quote updateQuote(Long id, Quote updatedQuote) {
        return quoteRepository.findById(id)
                .map(existing -> {
                    updatedQuote.setId(existing.getId());
                    return quoteRepository.save(updatedQuote);
                }).orElseThrow(() -> new RuntimeException("Quote not found"));
    }

    public void deleteQuote(Long id) {
        quoteRepository.deleteById(id);
    }


}