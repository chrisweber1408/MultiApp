package com.example.MultiAppBackend.homizer.storageItem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StorageItemService {

    private final StorageItemRepo storageItemRepo;
    public void saveStorageItem(StorageItem storageItem) {
        storageItemRepo.save(storageItem);
    }

    public List<StorageItem> getAllStorageItems() {
        List<StorageItem> storageItems = storageItemRepo.findAll();
        if (!storageItems.isEmpty()) {
            return storageItems;
        } else {
            throw new NoSuchElementException("List is empty");
        }
    }

    public StorageItem getOneStorageItem(String id) {
         return storageItemRepo.findById(id).get();
    }

    public void deleteStorageItem(String id) {
        storageItemRepo.deleteById(id);
    }
}
