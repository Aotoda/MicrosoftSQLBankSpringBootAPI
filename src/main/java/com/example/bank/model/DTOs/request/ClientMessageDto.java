package com.example.bank.model.DTOs.request;

import com.example.bank.model.entities.Client;
import com.example.bank.model.entities.ClientLog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientMessageDto {
    private Client client;
    private ClientLog log;
}
