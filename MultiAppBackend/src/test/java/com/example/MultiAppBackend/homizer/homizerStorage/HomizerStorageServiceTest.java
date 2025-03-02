package com.example.MultiAppBackend.homizer.homizerStorage;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.example.MultiAppBackend.user.MyUser;
import com.example.MultiAppBackend.user.MyUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class HomizerStorageServiceTest {

  @Test
  void saveHomizerStorage() {
    // Given
    HomizerStorageRepo homizerStorageRepo = Mockito.mock(HomizerStorageRepo.class);
    MyUserRepository myUserRepository = Mockito.mock(MyUserRepository.class);
    HomizerStorageService homizerStorageService = new HomizerStorageService(homizerStorageRepo, myUserRepository);
    MyUser myUser = new MyUser();
    HomizerStorage homizerStorage =
        HomizerStorage.builder().name("Test").description("Safe").image("Image").build();
    // When
    homizerStorageService.saveHomizerStorage(homizerStorage, myUser);
    // Then
    Mockito.verify(homizerStorageRepo).save(homizerStorage);
  }

  @Test
  void getAllHomizerStorages() {
    // Given
    HomizerStorageRepo homizerStorageRepo = Mockito.mock(HomizerStorageRepo.class);
    MyUserRepository myUserRepository = Mockito.mock(MyUserRepository.class);
    HomizerStorageService homizerStorageService = new HomizerStorageService(homizerStorageRepo, myUserRepository);
    HomizerStorage homizerStorage =
        HomizerStorage.builder().name("Test").description("Safe").image("Image").build();
    HomizerStorage homizerStorage2 =
        HomizerStorage.builder().name("Test2").description("Safe2").image("Image2").build();
    Mockito.when(homizerStorageRepo.findAll()).thenReturn(List.of(homizerStorage, homizerStorage2));
    // When
    List<HomizerStorage> allHomizerStorages = homizerStorageService.getAllHomizerStorages();
    // Then
    Assertions.assertThat(allHomizerStorages).hasSize(2);
  }

  @Test
  void getNoHomizerStorages() {
    // Given
    HomizerStorageRepo homizerStorageRepo = Mockito.mock(HomizerStorageRepo.class);
    MyUserRepository myUserRepository = Mockito.mock(MyUserRepository.class);
    HomizerStorageService homizerStorageService = new HomizerStorageService(homizerStorageRepo, myUserRepository);
    Mockito.when(homizerStorageRepo.findAll()).thenReturn(List.of());
    // Then
    Assertions.assertThatExceptionOfType(NoSuchElementException.class)
        .isThrownBy(homizerStorageService::getAllHomizerStorages);
  }

  @Test
  void getHomizerStorageById() {
    // Given
    HomizerStorageRepo homizerStorageRepo = Mockito.mock(HomizerStorageRepo.class);
    MyUserRepository myUserRepository = Mockito.mock(MyUserRepository.class);
    HomizerStorageService homizerStorageService = new HomizerStorageService(homizerStorageRepo, myUserRepository);
    final String id = "testId";
    final HomizerStorage homizerStorage =
        HomizerStorage.builder().name("Test1").description("Safe1").image("Image1").build();
    Mockito.when(homizerStorageRepo.findById(id)).thenReturn(Optional.of(homizerStorage));
    // When
    HomizerStorage oneHomizerStorage = homizerStorageService.getHomizerStorageById(id);
    // Then
    Assertions.assertThat(oneHomizerStorage.getName()).isEqualTo("Test1");
    Assertions.assertThat(oneHomizerStorage.getDescription()).isEqualTo("Safe1");
    Assertions.assertThat(oneHomizerStorage.getImage()).isEqualTo("Image1");
  }

  @Test
  void getNoStorageItem() {
    // Given
    HomizerStorageRepo homizerStorageRepo = Mockito.mock(HomizerStorageRepo.class);
    MyUserRepository myUserRepository = Mockito.mock(MyUserRepository.class);
    HomizerStorageService homizerStorageService = new HomizerStorageService(homizerStorageRepo, myUserRepository);
    final String id = "testId";
    Mockito.when(homizerStorageRepo.findById(id)).thenReturn(Optional.empty());
    // Then
    Assertions.assertThatExceptionOfType(NoSuchElementException.class)
        .isThrownBy(() -> homizerStorageService.getHomizerStorageById(id));
  }

  @Test
  void deleteHomizerItemById() {
    // Given
    HomizerStorageRepo homizerStorageRepo = Mockito.mock(HomizerStorageRepo.class);
    MyUserRepository myUserRepository = Mockito.mock(MyUserRepository.class);
    HomizerStorageService homizerStorageService = new HomizerStorageService(homizerStorageRepo, myUserRepository);
    final String id = "testId";
    final HomizerStorage homizerItem =
        HomizerStorage.builder().name("Test1").description("Safe1").image("Image1").build();
    Mockito.when(homizerStorageRepo.findById(id)).thenReturn(Optional.of(homizerItem));
    // When
    homizerStorageService.deleteHomizerStorageById(id);
    // Then
    Mockito.verify(homizerStorageRepo).deleteById(id);
  }

  @Test
  void deleteNoStorageItem() {
    // Given
    HomizerStorageRepo homizerStorageRepo = Mockito.mock(HomizerStorageRepo.class);
    MyUserRepository myUserRepository = Mockito.mock(MyUserRepository.class);
    HomizerStorageService homizerStorageService = new HomizerStorageService(homizerStorageRepo, myUserRepository);
    final String id = "testId";
    Mockito.when(homizerStorageRepo.findById(id)).thenReturn(Optional.empty());
    // Then
    Assertions.assertThatExceptionOfType(NoSuchElementException.class)
        .isThrownBy(() -> homizerStorageService.deleteHomizerStorageById(id));
  }
}
