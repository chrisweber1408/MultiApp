package com.example.MultiAppBackend.homizer.storageItem;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        Optional<StorageItem> storageItem = storageItemRepo.findById(id);
        if (storageItem.isPresent()) {
            return storageItem.get();
        } else {
            throw new NoSuchElementException("Item with id: " + id + " not found!");
        }
    }

    public void deleteStorageItem(String id) {
        Optional<StorageItem> storageItem = storageItemRepo.findById(id);
        if (storageItem.isPresent()) {
            storageItemRepo.deleteById(id);
        } else {
            throw new NoSuchElementException("Item with id: " + id + " not found!");
        }
    }
}
