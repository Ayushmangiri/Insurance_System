package com.umbrellainsur.insurance.repository;

import com.umbrellainsur.insurance.model.InjuriesCoverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InjuriesCoverageRepository extends JpaRepository<InjuriesCoverage, Long> {
}