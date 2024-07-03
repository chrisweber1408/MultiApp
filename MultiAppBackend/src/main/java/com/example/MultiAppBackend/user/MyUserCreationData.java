package com.example.MultiAppBackend.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUserCreationData {

    private String email;
    private String password;
    private String passwordRepeat;

}
