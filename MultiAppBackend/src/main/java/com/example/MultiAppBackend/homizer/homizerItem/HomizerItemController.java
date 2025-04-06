package com.example.MultiAppBackend.homizer.homizerItem;

import com.example.MultiAppBackend.user.MyUserRepository;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    homizerItemService.saveHomizerItem(homizerItemDto, principal);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("item/")
  public ResponseEntity<List<HomizerItemDto>> getAllHomizerItemsFromUser(Principal principal) {
    return ResponseEntity.ok(homizerItemService.getAllHomizerItemsFromUser(principal));
  }

  @GetMapping("item/{id}")
  public ResponseEntity<HomizerItemDto> getHomizerItemById(
      @PathVariable String id, Principal principal) {
    return ResponseEntity.ok(homizerItemService.getHomizerItemById(id, principal));
  }

  @DeleteMapping("item/delete/{id}")
  public ResponseEntity<Void> deleteHomizerItemById(@PathVariable String id, Principal principal) {
    homizerItemService.deleteHomizerItemById(id, principal);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("items/{id}")
  public ResponseEntity<List<HomizerItemDto>> getAllHomizerItemsInStorage(
      @PathVariable String id, Principal principal) {
    return ResponseEntity.ok(homizerItemService.getAllHomizerItemsInStorage(id, principal));
  }
}
