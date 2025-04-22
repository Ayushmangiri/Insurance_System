package com.umbrellainsur.insurance.repository;

import com.umbrellainsur.insurance.model.QuoteRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRatingRepository extends JpaRepository<QuoteRating, Long> {
}