package com.umbrellainsur.insurance.mapper;

import com.umbrellainsur.insurance.dto.ClientDTO;
import com.umbrellainsur.insurance.model.Client;

public class ClientMapper {

    public static ClientDTO toDTO(Client client) {
        if (client == null) return null;

        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        dto.setAddress(client.getAddress());
        dto.setPhone(client.getPhone());

        return dto;
    }

    public static Client toEntity(ClientDTO dto) {
        if (dto == null) return null;

        Client client = new Client();
        client.setId(dto.getId());
        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        client.setAddress(dto.getAddress());
        client.setPhone(dto.getPhone());

        return client;
    }
}