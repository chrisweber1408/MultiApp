package com.example.MultiAppBackend.homizer.homizerItem;

import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorage;
import com.example.MultiAppBackend.user.MyUser;
import jakarta.persistence.*;
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
public class HomizerItem {
  @Id private final String id = UUID.randomUUID().toString();
  private String name;
  private String description;
  private Integer number;

  @Column(name = "image", columnDefinition = "TEXT")
  private String image;

  @ManyToOne
  @JoinColumn(name = "homizer_storage_id")
  private HomizerStorage homizerStorage;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private MyUser user;

}
