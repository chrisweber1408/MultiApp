package com.example.MultiAppBackend.homizer.homizerStorage;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

import com.example.MultiAppBackend.user.MyUser;
import com.example.MultiAppBackend.user.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/homizer/storage/")
@RequiredArgsConstructor
public class HomizerStorageController {

  private final HomizerStorageService homizerStorageService;
  private final MyUserRepository myUserRepository;

  @PostMapping
  @CrossOrigin(origins = "http://localhost:4200")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Void> saveHomizerStorage(
      @RequestBody HomizerStorageDto homizerStorageDto, Principal principal) {
    try {
      MyUser myUser = myUserRepository.findByEmail(principal.getName()).orElseThrow();
      homizerStorageService.saveHomizerStorage(homizerStorageDto, myUser);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }

  @GetMapping
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<HomizerStorage>> getAllHomizerStoragesFromUser(Principal principal) {
    try {
      MyUser myUser = myUserRepository.findByEmail(principal.getName()).orElseThrow();
      return ResponseEntity.ok(homizerStorageService.getAllHomizerStoragesfromUser(myUser));
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @GetMapping("/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public HomizerStorage getHomizerStorageById(@PathVariable String id) {
    return homizerStorageService.getHomizerStorageById(id);
  }

  @DeleteMapping("/delete/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public void deleteHomizerStorageById(@PathVariable String id) {
    homizerStorageService.deleteHomizerStorageById(id);
  }
}
