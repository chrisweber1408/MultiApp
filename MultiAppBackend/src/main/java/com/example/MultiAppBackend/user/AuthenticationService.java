package com.example.MultiAppBackend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final MyUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;


    public MyUser signup(UserRegisterDto input) {
        if (Objects.equals(input.getPassword(), input.getPasswordRepeat())) {
            var myUser = new MyUser();
            myUser.setEmail(input.getEmail());
            myUser.setPassword(passwordEncoder.encode(input.getPassword()));
            userRepository.save(myUser);
        }
        return null;
    }

    public MyUser authenticate(UserLoginDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

}
