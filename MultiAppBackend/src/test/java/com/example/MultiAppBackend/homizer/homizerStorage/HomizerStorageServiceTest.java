package com.example.MultiAppBackend.homizer.homizerStorage;

import com.example.MultiAppBackend.user.MyUser;
import com.example.MultiAppBackend.user.MyUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HomizerStorageServiceTest {

  @Mock
  private HomizerStorageRepo homizerStorageRepo;

  @Mock
  private MyUserRepository myUserRepository;

  @InjectMocks
  private HomizerStorageService homizerStorageService;

  private MyUser myUser;
  private HomizerStorage homizerStorage;
  private HomizerStorageDto homizerStorageDto;

  @BeforeEach
  void setUp() {
    myUser = new MyUser();

    homizerStorage = new HomizerStorage();
    homizerStorage.setName("Test Storage");
    homizerStorage.setUser(myUser);

    homizerStorageDto = new HomizerStorageDto();
    homizerStorageDto.setId("storage123");
    homizerStorageDto.setName("Updated Storage");
    homizerStorageDto.setImage("image.png");
    homizerStorageDto.setDescription("Updated Description");
  }

  @Test
  void shouldSaveNewHomizerStorage() {
    when(homizerStorageRepo.findById(anyString())).thenReturn(Optional.empty());
    when(homizerStorageRepo.save(any(HomizerStorage.class))).thenReturn(homizerStorage);
    when(myUserRepository.save(any(MyUser.class))).thenReturn(myUser);

    homizerStorageService.saveHomizerStorage(homizerStorageDto, myUser);

    verify(homizerStorageRepo, times(1)).save(any(HomizerStorage.class));
    verify(myUserRepository, times(1)).save(any(MyUser.class));
  }

  @Test
  void shouldUpdateExistingHomizerStorage() {
    when(homizerStorageRepo.findById("storage123")).thenReturn(Optional.of(homizerStorage));
    when(homizerStorageRepo.save(any(HomizerStorage.class))).thenReturn(homizerStorage);
    when(myUserRepository.save(any(MyUser.class))).thenReturn(myUser);

    homizerStorageService.saveHomizerStorage(homizerStorageDto, myUser);

    assertEquals("Updated Storage", homizerStorage.getName());
    assertEquals("Updated Description", homizerStorage.getDescription());
    verify(homizerStorageRepo, times(1)).save(any(HomizerStorage.class));
  }

  @Test
  void shouldReturnAllHomizerStoragesFromUser() {
    when(homizerStorageRepo.findByUserId(myUser.getId())).thenReturn(List.of(homizerStorage));

    List<HomizerStorage> result = homizerStorageService.getAllHomizerStoragesfromUser(myUser);

    assertFalse(result.isEmpty());
    assertEquals(1, result.size());
    assertEquals("Test Storage", result.get(0).getName());
  }

  @Test
  void shouldThrowExceptionWhenUserHasNoStorages() {
    when(homizerStorageRepo.findByUserId(myUser.getId())).thenReturn(List.of());

    assertThrows(NoSuchElementException.class, () -> homizerStorageService.getAllHomizerStoragesfromUser(myUser));
  }

  @Test
  void shouldFindHomizerStorageById() {
    when(homizerStorageRepo.findById("storage123")).thenReturn(Optional.of(homizerStorage));

    HomizerStorage result = homizerStorageService.getHomizerStorageById("storage123");

    assertNotNull(result);
    assertEquals("Test Storage", result.getName());
  }

  @Test
  void shouldThrowExceptionWhenHomizerStorageNotFound() {
    when(homizerStorageRepo.findById("invalid-id")).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> homizerStorageService.getHomizerStorageById("invalid-id"));
  }

  @Test
  void shouldDeleteHomizerStorageById() {
    when(homizerStorageRepo.findById("storage123")).thenReturn(Optional.of(homizerStorage));
    doNothing().when(homizerStorageRepo).deleteById("storage123");

    homizerStorageService.deleteHomizerStorageById("storage123");

    verify(homizerStorageRepo, times(1)).deleteById("storage123");
  }

  @Test
  void shouldThrowExceptionWhenDeletingNonExistentHomizerStorage() {
    when(homizerStorageRepo.findById("invalid-id")).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> homizerStorageService.deleteHomizerStorageById("invalid-id"));
  }
}
