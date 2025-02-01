package com.example.MultiAppBackend.homizer.homizerItem;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.example.MultiAppBackend.homizer.dto.HomizerItemDto;
import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorageRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class HomizerItemServiceTest {

  @Test
  public void saveHomizerItem() {
    // Given
    HomizerItemDto homizerItemDto =
            HomizerItemDto.builder().name("Test").description("Safe").image("Image").number(1).build();
    HomizerItemRepo homizerItemRepo = Mockito.mock(HomizerItemRepo.class);
    HomizerStorageRepo homizerStorageRepo = Mockito.mock(HomizerStorageRepo.class);
    HomizerItemService homizerItemService =
        new HomizerItemService(homizerItemRepo, homizerStorageRepo);
    // When
    homizerItemService.saveHomizerItem(homizerItemDto);
    // Then
//    Mockito.verify(homizerItemRepo).save(homizerItemDto);
  }

  @Test
  void getAllHomizerItems() {
    // Given
    HomizerItem homizerItem1 =
        HomizerItem.builder().name("Test1").description("Safe1").image("Image1").number(1).build();
    HomizerItem homizerItem2 =
        HomizerItem.builder().name("Test2").description("Safe2").image("Image2").number(2).build();
    HomizerItemRepo homizerItemRepo = Mockito.mock(HomizerItemRepo.class);
    HomizerStorageRepo homizerStorageRepo = Mockito.mock(HomizerStorageRepo.class);
    HomizerItemService homizerItemService =
        new HomizerItemService(homizerItemRepo, homizerStorageRepo);
    Mockito.when(homizerItemRepo.findAll()).thenReturn(List.of(homizerItem1, homizerItem2));
    // When
    List<HomizerItem> allHomizerItems = homizerItemService.getAllHomizerItems();
    // Then
    Assertions.assertThat(allHomizerItems).hasSize(2);
  }

  @Test
  void getNoHomizerItems() {
    // Given
    HomizerItemRepo homizerItemRepo = Mockito.mock(HomizerItemRepo.class);
    HomizerStorageRepo homizerStorageRepo = Mockito.mock(HomizerStorageRepo.class);
    HomizerItemService homizerItemService =
        new HomizerItemService(homizerItemRepo, homizerStorageRepo);
    Mockito.when(homizerItemRepo.findAll()).thenReturn(List.of());
    // Then
    Assertions.assertThatExceptionOfType(NoSuchElementException.class)
        .isThrownBy(homizerItemService::getAllHomizerItems);
  }

  @Test
  void getHomizerItemById() {
    // Given
    HomizerItemRepo homizerItemRepo = Mockito.mock(HomizerItemRepo.class);
    HomizerStorageRepo homizerStorageRepo = Mockito.mock(HomizerStorageRepo.class);
    HomizerItemService homizerItemService =
        new HomizerItemService(homizerItemRepo, homizerStorageRepo);
    final String id = "testId";
    final HomizerItem homizerItem =
        HomizerItem.builder().name("Test1").description("Safe1").image("Image1").number(1).build();
    Mockito.when(homizerItemRepo.findById(id)).thenReturn(Optional.of(homizerItem));
    // When
    HomizerItem oneHomizerItem = homizerItemService.getHomizerItemById(id);
    // Then
    Assertions.assertThat(oneHomizerItem.getName()).isEqualTo("Test1");
    Assertions.assertThat(oneHomizerItem.getDescription()).isEqualTo("Safe1");
    Assertions.assertThat(oneHomizerItem.getImage()).isEqualTo("Image1");
    Assertions.assertThat(oneHomizerItem.getNumber()).isEqualTo(1);
  }

  @Test
  void getNoStorageItem() {
    // Given
    HomizerItemRepo homizerItemRepo = Mockito.mock(HomizerItemRepo.class);
    HomizerStorageRepo homizerStorageRepo = Mockito.mock(HomizerStorageRepo.class);
    HomizerItemService homizerItemService =
        new HomizerItemService(homizerItemRepo, homizerStorageRepo);
    final String id = "testId";
    Mockito.when(homizerItemRepo.findById(id)).thenReturn(Optional.empty());
    // Then
    Assertions.assertThatExceptionOfType(NoSuchElementException.class)
        .isThrownBy(() -> homizerItemService.getHomizerItemById(id));
  }

  @Test
  void deleteHomizerItemById() {
    // Given
    HomizerItemRepo homizerItemRepo = Mockito.mock(HomizerItemRepo.class);
    HomizerStorageRepo homizerStorageRepo = Mockito.mock(HomizerStorageRepo.class);
    HomizerItemService homizerItemService =
        new HomizerItemService(homizerItemRepo, homizerStorageRepo);
    final String id = "testId";
    final HomizerItem homizerItem =
        HomizerItem.builder().name("Test1").description("Safe1").image("Image1").number(1).build();
    Mockito.when(homizerItemRepo.findById(id)).thenReturn(Optional.of(homizerItem));
    // When
    homizerItemService.deleteHomizerItemById(id);
    // Then
    Mockito.verify(homizerItemRepo).deleteById(id);
  }

  @Test
  void deleteNoStorageItem() {
    // Given
    HomizerItemRepo homizerItemRepo = Mockito.mock(HomizerItemRepo.class);
    HomizerStorageRepo homizerStorageRepo = Mockito.mock(HomizerStorageRepo.class);
    HomizerItemService homizerItemService =
        new HomizerItemService(homizerItemRepo, homizerStorageRepo);
    final String id = "testId";
    Mockito.when(homizerItemRepo.findById(id)).thenReturn(Optional.empty());
    // Then
    Assertions.assertThatExceptionOfType(NoSuchElementException.class)
        .isThrownBy(() -> homizerItemService.deleteHomizerItemById(id));
  }
}
