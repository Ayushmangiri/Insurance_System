package com.umbrellainsur.insurance.service;

import com.umbrellainsur.insurance.model.Broker;
import com.umbrellainsur.insurance.model.Client;
import com.umbrellainsur.insurance.repository.BrokerRepository;
import com.umbrellainsur.insurance.repository.QuoteRepository;
import com.umbrellainsur.insurance.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private  ClientRepository clientRepository;
    private  BrokerRepository brokerRepository;
    private QuoteRepository quoteRepository;
    public Client createClient(Client client, Long brokerId) {
        Broker broker = brokerRepository.findById(brokerId).orElseThrow(() -> new RuntimeException("Broker not found"));
        client.setBroker(broker);
        return clientRepository.save(client);
    }

    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    public List<Client> getClientsByBroker(Long brokerId) {
        return clientRepository.findByBrokerId(brokerId);
    }
}