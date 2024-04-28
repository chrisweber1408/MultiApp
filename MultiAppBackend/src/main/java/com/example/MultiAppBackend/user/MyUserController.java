package com.example.MultiAppBackend.user;

import com.example.MultiAppBackend.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MyUserController {

    private final MyUserDetailsService myUserService;

    private final AuthenticationService authenticationService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> registerMyUser(@RequestBody MyUserCreationData myUserCreationData){
        try {
            authenticationService.register(myUserCreationData);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
