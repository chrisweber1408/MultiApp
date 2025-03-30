package com.example.MultiAppBackend.homizer.homizerItem;

import com.example.MultiAppBackend.user.MyUser;
import com.example.MultiAppBackend.user.MyUserRepository;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/homizer/")
@RequiredArgsConstructor
public class HomizerItemController {


  private final HomizerItemService homizerItemService;
  private final MyUserRepository myUserRepository;

  @PostMapping("item/")
  public ResponseEntity<Void> saveHomizerItem(
          @RequestBody HomizerItemDto homizerItemDto, Principal principal) {
    MyUser myUser = myUserRepository.findByEmail(principal.getName())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    homizerItemService.saveHomizerItem(homizerItemDto, myUser);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("item/")
  public ResponseEntity<List<HomizerItemDto>> getAllHomizerItemsFromUser(Principal principal) {
    MyUser myUser = myUserRepository.findByEmail(principal.getName())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    return ResponseEntity.ok(homizerItemService.getAllHomizerItemsFromUser(myUser));
  }

  @GetMapping("item/{id}")
  public ResponseEntity<HomizerItemDto> getHomizerItemById(@PathVariable String id) {
    return ResponseEntity.ok(homizerItemService.getHomizerItemById(id));
  }

  @DeleteMapping("item/delete/{id}")
  public ResponseEntity<Void> deleteHomizerItemById(@PathVariable String id) {
    homizerItemService.deleteHomizerItemById(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("items/{id}")
  public ResponseEntity<List<HomizerItemDto>> getAllHomizerItemsInStorage(@PathVariable String id) {
    return ResponseEntity.ok(homizerItemService.getAllHomizerItemsInStorage(id));
  }
}
