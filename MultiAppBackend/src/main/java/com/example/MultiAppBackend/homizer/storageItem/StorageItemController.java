package com.example.MultiAppBackend.homizer.storageItem;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/homizer/storageItem")
@RequiredArgsConstructor
public class StorageItemController {

    private final StorageItemService storageItemService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStorageItem(@RequestBody StorageItem storageItem){
        storageItemService.saveStorageItem(storageItem);
    }
}
