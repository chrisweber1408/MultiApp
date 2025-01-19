package com.example.MultiAppBackend.homizer.storageItem;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/homizer/item/")
@RequiredArgsConstructor
public class StorageItemController {

    private final StorageItemService storageItemService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveStorageItem(@RequestBody StorageItem storageItem) {
        try {
            storageItemService.saveStorageItem(storageItem);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<StorageItem>> getAllStorageItems() {
        try {
            return ResponseEntity.ok(storageItemService.getAllStorageItems());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public StorageItem getOneStorageItem(@PathVariable String id) {
        return storageItemService.getOneStorageItem(id);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void deleteStorageItem(@PathVariable String id) {
        storageItemService.deleteStorageItem(id);
    }

}
