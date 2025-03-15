package com.example.MultiAppBackend.homizer.homizerItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    private String homizerStorageName;
}
