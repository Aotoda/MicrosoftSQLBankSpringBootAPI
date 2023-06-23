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
@Table(name = "MAGANG_JOHN_LOGS")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientLog {
    @Id
    @Column(name = "logId", columnDefinition = "uniqueidentifier")
    private UUID logId;

    private String message;
    private String timeStamp;

    public ClientLog(String message, String timeStamp){
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
