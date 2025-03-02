package com.example.MultiAppBackend.homizer.homizerStorage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomizerStorageDto {
  private String id;
  private String name;
  private String description;
  private String image;
}
