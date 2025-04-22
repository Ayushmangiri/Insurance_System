package com.umbrellainsur.insurance.repository;

import com.umbrellainsur.insurance.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findByBrokerId(Long brokerId);
}