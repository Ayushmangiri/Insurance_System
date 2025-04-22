package com.umbrellainsur.insurance.controller;

import com.umbrellainsur.insurance.model.Client;
import com.umbrellainsur.insurance.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/{brokerId}")
    public ResponseEntity<Client> createClient(@RequestBody Client client, @PathVariable Long brokerId) {
        Client created = clientService.createClient(client, brokerId);
        return ResponseEntity.ok(created);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId) {
        clientService.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/broker/{brokerId}")
    public ResponseEntity<List<Client>> getClientsByBroker(@PathVariable Long brokerId) {
        return ResponseEntity.ok(clientService.getClientsByBroker(brokerId));
    }
}
