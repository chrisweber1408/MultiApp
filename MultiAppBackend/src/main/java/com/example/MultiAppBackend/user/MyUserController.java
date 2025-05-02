package com.example.MultiAppBackend.user;

import com.example.MultiAppBackend.security.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/api/user")
@RestController
public class MyUserController {
  private final JwtService jwtService;
  private final MyUserService myUserService;

  public MyUserController(JwtService jwtService, MyUserService myUserService) {
    this.jwtService = jwtService;
    this.myUserService = myUserService;
  }

  @PostMapping("/register")
  public ResponseEntity<MyUser> register(@RequestBody UserRegisterDto registerUserDto) {
    MyUser registeredUser = myUserService.signup(registerUserDto);

    return ResponseEntity.ok(registeredUser);
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> authenticate(
      @RequestBody UserLoginDto loginUserDto, HttpServletResponse response) {
    MyUser authenticatedUser = myUserService.authenticate(loginUserDto);

    String jwtToken = jwtService.generateToken(authenticatedUser);

    Cookie authenticationCookie = new Cookie("authentication", jwtToken);
    authenticationCookie.setHttpOnly(true);
    authenticationCookie.setMaxAge((int) jwtService.getExpirationTime());

    response.addCookie(authenticationCookie);
    response.setStatus(HttpStatus.OK.value());

    LoginResponse loginResponse =
        new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

    return ResponseEntity.ok(loginResponse);
  }

  @GetMapping("/infos")
  public ResponseEntity<UserInfosDto> getUserInfos(Principal principal) {
    return ResponseEntity.ok(myUserService.getUserInfos(principal));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<Void> deleteUser(Principal principal) {
    myUserService.deleteUser(principal);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
