package com.example.bank.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.model.entities.ClientLog;


public interface ClientLogRepo extends JpaRepository<ClientLog,UUID>{

}