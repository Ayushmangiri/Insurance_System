package com.umbrellainsur.insurance.repository;

import com.umbrellainsur.insurance.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByBrokerId(Long brokerId);
}
