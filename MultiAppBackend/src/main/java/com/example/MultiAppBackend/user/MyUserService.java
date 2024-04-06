package com.example.MultiAppBackend.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserService {

    private final MyUserRepository myUserRepository;
    private final PasswordEncoder passwordEncoder;

    public void createNewUser(MyUserCreationData myUserCreationData){

        String password = myUserCreationData.getPassword();
        String passwordRepeat = myUserCreationData.getPasswordRepeat();
        String username = myUserCreationData.getUsername();

        if (!Objects.equals(password, passwordRepeat)){
            throw new IllegalArgumentException("passwords do not match");
        }
        if (username == null || username.isBlank()){
            throw new IllegalArgumentException("username is blank");
        }
        MyUser myUser = new MyUser();
        myUser.setUsername(username);
        myUser.setPassword(passwordEncoder.encode(password));
        myUser.setRoles(List.of("user"));
        myUserRepository.save(myUser);
    }

    public Optional<MyUser> findByUsername(String username){
        return myUserRepository.findByUsername(username);
    }

    public Optional<MyUser> findById(String id){
        return myUserRepository.findById(id);
    }

}
