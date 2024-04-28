package com.example.MultiAppBackend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {



    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginData loginData){
        System.out.println("ja");
        return null;

    }

}
