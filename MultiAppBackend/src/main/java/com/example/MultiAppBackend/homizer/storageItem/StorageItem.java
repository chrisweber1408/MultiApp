package com.example.MultiAppBackend.homizer.storageItem;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageItem {
    @Id
    private final String id = UUID.randomUUID().toString();
    private String name;
    private String description;
    private String image;
    private Integer number;

}
