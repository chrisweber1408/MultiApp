package com.example.MultiAppBackend.homizer.homizerItem;

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
@RequestMapping("/api/homizer/")
@RequiredArgsConstructor
public class HomizerItemController {

  private final HomizerItemService homizerItemService;
  private final MyUserRepository myUserRepository;

  @PostMapping("item/")
  @CrossOrigin(origins = "http://localhost:4200")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Void> saveHomizerItem(
      @RequestBody HomizerItemDto homizerItemDto, Principal principal) {
    try {
      MyUser myUser = myUserRepository.findByEmail(principal.getName()).orElseThrow();
      homizerItemService.saveHomizerItem(homizerItemDto, myUser);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }

  @GetMapping("item/")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<HomizerItemDto>> getAllHomizerItemsFromUser(Principal principal) {
    try {
      MyUser myUser = myUserRepository.findByEmail(principal.getName()).orElseThrow();
      return ResponseEntity.ok(homizerItemService.getAllHomizerItemsFromUser(myUser));
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @GetMapping("item/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public HomizerItemDto getHomizerItemById(@PathVariable String id) {
    return homizerItemService.getHomizerItemById(id);
  }

  @DeleteMapping("item/delete/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public void deleteHomizerItemById(@PathVariable String id) {
    homizerItemService.deleteHomizerItemById(id);
  }

  @GetMapping("items/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public List<HomizerItemDto> getAllHomizerItemsInStorage(@PathVariable String id) {
    return homizerItemService.getAllHomizerItemsInStorage(id);
  }
}
