package com.umbrellainsur.insurance.repository;

import com.umbrellainsur.insurance.model.PropertyCoverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyCoverageRepository extends JpaRepository<PropertyCoverage, Long> {
}