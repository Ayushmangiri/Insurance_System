package com.umbrellainsur.insurance.repository;

import com.umbrellainsur.insurance.model.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrokerRepository extends JpaRepository<Broker, Long> {
    Optional<Broker> findByEmail(String email);
    boolean existsByEmail(String email);
}