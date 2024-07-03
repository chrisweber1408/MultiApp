package com.example.MultiAppBackend.user;

import lombok.Data;

@Data
public class UserRegisterDto {

    private String email;
    private String password;
    private String passwordRepeat;

}
