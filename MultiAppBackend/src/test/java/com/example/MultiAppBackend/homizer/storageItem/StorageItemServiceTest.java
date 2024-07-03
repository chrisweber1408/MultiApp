package com.example.MultiAppBackend.homizer.storageItem;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StorageItemServiceTest {

    @Test
    void saveStorageItem() {
        //Given
        StorageItem storageItem = StorageItem.builder()
                .name("Test")
                .description("Safe")
                .image("Image")
                .number(1)
                .build();
        StorageItemRepo storageItemRepo = Mockito.mock(StorageItemRepo.class);
        StorageItemService storageItemService = new StorageItemService(storageItemRepo);
        Mockito.when(storageItemRepo.save(storageItem)).thenReturn(storageItem);
        //When
        storageItemService.saveStorageItem(storageItem);
        //Then
        Mockito.verify(storageItemRepo).save(storageItem);
    }

    @Test
    void getAllStorageItems() {
    }

    @Test
    void getOneStorageItem() {
    }

    @Test
    void deleteStorageItem() {
    }
}