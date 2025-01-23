package com.example.MultiAppBackend.homizer.homizerStorage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomizerStorage {

  @Id private final String id = UUID.randomUUID().toString();
  private String name;
  private String description;

  @Column(name = "image", columnDefinition = "TEXT")
  private String image;
}
