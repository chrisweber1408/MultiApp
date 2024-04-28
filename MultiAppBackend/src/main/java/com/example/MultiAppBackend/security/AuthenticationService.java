package com.example.MultiAppBackend.security;

import com.example.MultiAppBackend.user.MyUser;
import com.example.MultiAppBackend.user.MyUserCreationData;
import com.example.MultiAppBackend.user.MyUserRepository;
import com.example.MultiAppBackend.user.MyUserRole;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    private final MyUserRepository myUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final TokenRepository tokenRepository;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(MyUserRepository myUserRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 TokenRepository tokenRepository,
                                 AuthenticationManager authenticationManager) {
        this.myUserRepository = myUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(MyUserCreationData myUserCreationData) {

        // check if user already exist. if exist than authenticate the user
        if(myUserRepository.findByEmail(myUserCreationData.getEmail()).isPresent()) {
            return new AuthenticationResponse(null, "User already exist");
        }

        MyUser myUser = new MyUser();
        myUser.setEmail(myUserCreationData.getEmail());
        myUser.setPassword(passwordEncoder.encode(myUserCreationData.getPassword()));


        myUser.setRole(MyUserRole.USER);

        myUser = myUserRepository.save(myUser);

        String jwt = jwtService.generateToken(myUser);

        saveUserToken(jwt, myUser);

        return new AuthenticationResponse(jwt, "User registration was successful");

    }

    public AuthenticationResponse authenticate(MyUser request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        MyUser user = myUserRepository.findByEmail(request.getUsername()).orElseThrow();
        String jwt = jwtService.generateToken(user);

        revokeAllTokenByUser(user);
        saveUserToken(jwt, user);

        return new AuthenticationResponse(jwt, "User login was successful");

    }
    private void revokeAllTokenByUser(MyUser user) {
        List<Token> validTokens = tokenRepository.findAllTokensByUser(user.getId());
        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }
    private void saveUserToken(String jwt, MyUser user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setMyUser(user);
        tokenRepository.save(token);
    }
}
