package com.example.bank.model.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MAGANG_JOHN_CLIENTS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @Column(name = "clientId", columnDefinition = "uniqueidentifier")
    private UUID clientId;

    private Integer number;
    private String firstName;
    private String lastName;

    public Client(Integer number, String firstName, String lastName){
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}