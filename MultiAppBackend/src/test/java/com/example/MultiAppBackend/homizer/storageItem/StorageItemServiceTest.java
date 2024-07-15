package com.example.MultiAppBackend.homizer.storageItem;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.NoSuchElementException;
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
        //Given
        StorageItem storageItem1 = StorageItem.builder()
                .name("Test1")
                .description("Safe1")
                .image("Image1")
                .number(1)
                .build();
        StorageItem storageItem2 = StorageItem.builder()
                .name("Test2")
                .description("Safe2")
                .image("Image2")
                .number(2)
                .build();
        StorageItemRepo storageItemRepo = Mockito.mock(StorageItemRepo.class);
        StorageItemService storageItemService = new StorageItemService(storageItemRepo);
        Mockito.when(storageItemRepo.findAll()).thenReturn(List.of(storageItem1, storageItem2));
        //When
        List<StorageItem> allStorageItems = storageItemService.getAllStorageItems();
        //Then
        Assertions.assertThat(allStorageItems).hasSize(2);
    }

    @Test
    void getNoStorageItems() {
        //Given
        StorageItemRepo storageItemRepo = Mockito.mock(StorageItemRepo.class);
        StorageItemService storageItemService = new StorageItemService(storageItemRepo);
        Mockito.when(storageItemRepo.findAll()).thenReturn(List.of());
        //Then
        Assertions.assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(storageItemService::getAllStorageItems);
    }

    @Test
    void getOneStorageItem() {
        //Given
        StorageItemRepo storageItemRepo = Mockito.mock(StorageItemRepo.class);
        StorageItemService storageItemService = new StorageItemService(storageItemRepo);
        final String id = "testId";
        final StorageItem storageItem = StorageItem.builder()
                .name("Test1")
                .description("Safe1")
                .image("Image1")
                .number(1)
                .build();
        Mockito.when(storageItemRepo.findById(id)).thenReturn(Optional.of(storageItem));
        //When
        StorageItem oneStorageItem = storageItemService.getOneStorageItem(id);
        //Then
        Assertions.assertThat(oneStorageItem.getName()).isEqualTo("Test1");
        Assertions.assertThat(oneStorageItem.getDescription()).isEqualTo("Safe1");
        Assertions.assertThat(oneStorageItem.getImage()).isEqualTo("Image1");
        Assertions.assertThat(oneStorageItem.getNumber()).isEqualTo(1);
    }

    @Test
    void getNoStorageItem() {
        //Given
        StorageItemRepo storageItemRepo = Mockito.mock(StorageItemRepo.class);
        StorageItemService storageItemService = new StorageItemService(storageItemRepo);
        final String id = "testId";
        Mockito.when(storageItemRepo.findById(id)).thenReturn(Optional.empty());
        //Then
        Assertions.assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(() -> storageItemService.getOneStorageItem(id));
    }

    @Test
    void deleteStorageItem() {
        //Given
        StorageItemRepo storageItemRepo = Mockito.mock(StorageItemRepo.class);
        StorageItemService storageItemService = new StorageItemService(storageItemRepo);
        final String id = "testId";
        final StorageItem storageItem = StorageItem.builder()
                .name("Test1")
                .description("Safe1")
                .image("Image1")
                .number(1)
                .build();
        Mockito.when(storageItemRepo.findById(id)).thenReturn(Optional.of(storageItem));
        //When
        storageItemService.deleteStorageItem(id);
        //Then
        Mockito.verify(storageItemRepo).deleteById(id);
    }

    @Test
    void deleteNoStorageItem(){
        //Given
        StorageItemRepo storageItemRepo = Mockito.mock(StorageItemRepo.class);
        StorageItemService storageItemService = new StorageItemService(storageItemRepo);
        final String id = "testId";
        Mockito.when(storageItemRepo.findById(id)).thenReturn(Optional.empty());
        //Then
        Assertions.assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(() -> storageItemService.deleteStorageItem(id));
    }
}