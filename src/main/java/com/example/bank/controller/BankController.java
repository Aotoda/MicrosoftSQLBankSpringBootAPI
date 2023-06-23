package com.example.bank.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.model.DTOs.request.ClientMessageDto;
import com.example.bank.model.entities.Client;
import com.example.bank.model.entities.ClientLog;
import com.example.bank.repo.ClientLogRepo;
import com.example.bank.repo.MySqlRepo;

@RestController
public class BankController {
    
    @Autowired
    MySqlRepo mySqlRepo;

    @Autowired
    ClientLogRepo logRepo;

    @GetMapping("/get-all-Clients")
    public List<Client> getAllClients(){

        return mySqlRepo.findAll();

    }

    @GetMapping("/get-Client/{identity}")
    public Client getSingleClients(@PathVariable("identity") UUID identity){

        return mySqlRepo.findById(identity).get();

    }

    @DeleteMapping("/remove/{identity}")
    public boolean removeRow(@PathVariable("identity") UUID identity){
        if(!mySqlRepo.findById(identity).equals(Optional.empty())){
            mySqlRepo.deleteById(identity);
            String message = "Removed row " + identity + "!";
            logRepo.save(new ClientLog(message, LocalDateTime.now().toString()));
            return true;
        }
        return false;
    }

    @PutMapping("/update/{identity}")
    public Client updateRow(@PathVariable("identity") UUID identity, @RequestBody Client body){
        Client current = mySqlRepo.findById(identity).get();
        current.setNumber(body.getNumber());
        current.setFirstName(body.getFirstName());
        current.setLastName(body.getLastName());

        String message = "Updated row " + identity + "!";
        logRepo.save(new ClientLog(message, LocalDateTime.now().toString()));
        return mySqlRepo.save(current);
    }

    @PostMapping("/create")
    public Client createRow(@RequestBody Client body) {
        mySqlRepo.save(body);
        String message = "Created row " + body.getClientId() + "!";
        logRepo.save(new ClientLog(message, LocalDateTime.now().toString()));
        return body;
    }

    @PostMapping("/createDto")
    public Client createRow(@RequestBody ClientMessageDto body) {
        mySqlRepo.save(body.getClient());
        body.getLog().setTimeStamp(LocalDateTime.now().toString());
        logRepo.save(body.getLog());
        return body.getClient();
    }

}