package com.example.MultiAppBackend.homizer.storageItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StorageItem {
    @Id
    private final String id = UUID.randomUUID().toString();
    private String name;
    private String description;
    private Integer number;
    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

}
