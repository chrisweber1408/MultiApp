package com.example.MultiAppBackend.homizer.homizerItem;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/homizer/item/")
@RequiredArgsConstructor
public class HomizerItemController {

    private final HomizerItemService homizerItemService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveHomizerItem(@RequestBody HomizerItem homizerItem) {
        try {
            homizerItemService.saveHomizerItem(homizerItem);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<HomizerItem>> getAllHomizerItems() {
        try {
            return ResponseEntity.ok(homizerItemService.getAllHomizerItems());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public HomizerItem getHomizerItemById(@PathVariable String id) {
        return homizerItemService.getHomizerItemById(id);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void deleteHomizerItemById(@PathVariable String id) {
        homizerItemService.deleteHomizerItemById(id);
    }

}
