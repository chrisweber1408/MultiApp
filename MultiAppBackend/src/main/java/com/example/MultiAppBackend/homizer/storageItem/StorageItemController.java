package com.example.MultiAppBackend.homizer.storageItem;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/homizer/storageItem")
@RequiredArgsConstructor
public class StorageItemController {

    private final StorageItemService storageItemService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveStorageItem(@RequestBody StorageItem storageItem){
        storageItemService.saveStorageItem(storageItem);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public List<StorageItem> getAllStorageItems(){
        return storageItemService.getAllStorageItems();
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
