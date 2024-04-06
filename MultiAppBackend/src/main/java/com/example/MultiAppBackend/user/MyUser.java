package com.example.MultiAppBackend.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MyUser {

    @Id
    private String id;
    @Column(unique = true)
    private String username;
    private String password;
    private List<String> roles;

}
