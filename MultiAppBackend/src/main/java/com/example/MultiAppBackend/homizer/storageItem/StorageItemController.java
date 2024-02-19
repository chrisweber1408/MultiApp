package com.example.MultiAppBackend.homizer.storageItem;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/homizer/storageItem")
@RequiredArgsConstructor
public class StorageItemController {

    private StorageItemService storageItemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStorageItem(StorageItem storageItem){
        storageItemService.saveStorageItem(storageItem);
    }

}
