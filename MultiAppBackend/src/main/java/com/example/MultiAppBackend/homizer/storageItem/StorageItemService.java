package com.example.MultiAppBackend.homizer.storageItem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageItemService {

    private final StorageItemRepo storageItemRepo;
    public void saveStorageItem(StorageItem storageItem) {
        storageItemRepo.save(storageItem);
    }

    public List<StorageItem> getAllStorageItems() {
        return storageItemRepo.findAll();
    }
}
