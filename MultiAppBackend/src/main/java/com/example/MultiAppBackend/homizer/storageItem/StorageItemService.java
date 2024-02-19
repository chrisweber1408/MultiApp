package com.example.MultiAppBackend.homizer.storageItem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageItemService {

    private StorageItemRepo storageItemRepo;
    public void saveStorageItem(StorageItem storageItem) {
        storageItemRepo.save(storageItem);
    }
}
