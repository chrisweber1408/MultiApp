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
    homizerStorageService.saveHomizerStorage(homizerStorageDto, principal);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  public ResponseEntity<List<HomizerStorageDto>> getAllHomizerStoragesFromUser(Principal principal) {
    return ResponseEntity.ok(homizerStorageService.getAllHomizerStoragesfromUser(principal));
  }

  @GetMapping("/{id}")
  public ResponseEntity<HomizerStorageDto> getHomizerStorageById(@PathVariable String id, Principal principal) {
    return ResponseEntity.ok(homizerStorageService.getHomizerStorageById(id, principal));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteHomizerStorageById(@PathVariable String id, Principal principal) {
    homizerStorageService.deleteHomizerStorageById(id, principal);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
