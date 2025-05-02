package com.example.MultiAppBackend.user;

import com.example.MultiAppBackend.homizer.homizerItem.HomizerItemService;
import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorageService;
import jakarta.transaction.Transactional;
import java.security.Principal;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserService {

  private final MyUserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final AuthenticationManager authenticationManager;

  private final HomizerItemService homizerItemService;

  private final HomizerStorageService homizerStorageService;

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
        new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));

    return userRepository.findByEmail(input.getEmail()).orElseThrow();
  }

  public UserInfosDto getUserInfos(Principal principal) {
    UserInfosDto userInfosDto = new UserInfosDto();
    userInfosDto.setEmail(principal.getName());
    userInfosDto.setItemCount(homizerItemService.getAllHomizerItemsFromUser(principal).size());
    userInfosDto.setStorageCount(
        homizerStorageService.getAllHomizerStoragesfromUser(principal).size());
    return userInfosDto;
  }

  @Transactional
  public void deleteUser(Principal principal) {
    // Delete items
    homizerItemService
        .getAllHomizerItemsFromUser(principal)
        .forEach(
            homizerItemDto ->
                homizerItemService.deleteHomizerItemById(homizerItemDto.getId(), principal));
    // Delete storages
    homizerStorageService
        .getAllHomizerStoragesfromUser(principal)
        .forEach(
            homizerStorageDto ->
                homizerStorageService.deleteHomizerStorageById(
                    homizerStorageDto.getId(), principal));
    // Delete user
    userRepository.findByEmail(principal.getName()).ifPresent(userRepository::delete);
  }
}
