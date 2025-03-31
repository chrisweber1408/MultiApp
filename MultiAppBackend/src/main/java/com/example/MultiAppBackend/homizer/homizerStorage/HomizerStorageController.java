package com.example.MultiAppBackend.homizer.homizerStorage;

import com.example.MultiAppBackend.user.MyUser;
import com.example.MultiAppBackend.user.MyUserRepository;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/homizer/storage/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class HomizerStorageController {

  private final HomizerStorageService homizerStorageService;
  private final MyUserRepository myUserRepository;

  @PostMapping
  public ResponseEntity<Void> saveHomizerStorage(
      @RequestBody HomizerStorageDto homizerStorageDto, Principal principal) {
    MyUser myUser = myUserRepository.findByEmail(principal.getName())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    homizerStorageService.saveHomizerStorage(homizerStorageDto, myUser);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  public ResponseEntity<List<HomizerStorageDto>> getAllHomizerStoragesFromUser(
      Principal principal) {
    return myUserRepository
        .findByEmail(principal.getName())
        .map(user -> ResponseEntity.ok(homizerStorageService.getAllHomizerStoragesfromUser(user)))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/{id}")
  public ResponseEntity<HomizerStorageDto> getHomizerStorageById(@PathVariable String id) {
    return ResponseEntity.ok(homizerStorageService.getHomizerStorageById(id));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteHomizerStorageById(@PathVariable String id) {
    homizerStorageService.deleteHomizerStorageById(id);
    return ResponseEntity.noContent().build();
  }
}
