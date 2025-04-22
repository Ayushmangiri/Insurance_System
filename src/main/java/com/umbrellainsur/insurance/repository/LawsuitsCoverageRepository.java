package com.umbrellainsur.insurance.repository;

import com.umbrellainsur.insurance.model.LawsuitsCoverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LawsuitsCoverageRepository extends JpaRepository<LawsuitsCoverage, Long> {
}