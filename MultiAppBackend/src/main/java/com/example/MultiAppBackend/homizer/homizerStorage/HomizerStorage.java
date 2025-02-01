package com.example.MultiAppBackend.homizer.homizerStorage;

import com.example.MultiAppBackend.homizer.homizerItem.HomizerItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.OneToMany;
import lombok.*;

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

  @OneToMany private List<HomizerItem> homizerItems;
}
