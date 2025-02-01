package com.example.MultiAppBackend.homizer.dto;

import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorage;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomizerItemDto {
    private String id;
    private String name;
    private String description;
    private Integer number;
    private String image;
    private String homizerStorageId;
}
