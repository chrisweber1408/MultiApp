package com.example.MultiAppBackend.homizer.homizerStorage;

import com.example.MultiAppBackend.user.MyUser;
import com.example.MultiAppBackend.user.MyUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HomizerStorageServiceTest {

  @InjectMocks
  private HomizerStorageService homizerStorageService;

  @Mock
  private HomizerStorageRepo homizerStorageRepo;

  @Mock
  private MyUserRepository myUserRepository;

  private MyUser mockUser;
  private HomizerStorageDto mockStorageDto;
  private HomizerStorage mockStorage;

  @BeforeEach
  void setUp() {
    mockUser = new MyUser();
    mockUser.setEmail("test@example.com");

    mockStorageDto = new HomizerStorageDto("456", "Test Storage", "Description", "image.png");

    mockStorage = new HomizerStorage();
    mockStorage.setName("Test Storage");
    mockStorage.setDescription("Description");
    mockStorage.setImage("image.png");
    mockStorage.setUser(mockUser);
  }

  @Test
  void saveHomizerStorage_ShouldSaveNewStorage_WhenIdIsNull() {
    mockStorageDto.setId(null);
    when(homizerStorageRepo.save(any(HomizerStorage.class))).thenReturn(mockStorage);

    homizerStorageService.saveHomizerStorage(mockStorageDto, mockUser);

    verify(homizerStorageRepo, times(1)).save(any(HomizerStorage.class));
  }

  @Test
  void saveHomizerStorage_ShouldUpdateExistingStorage_WhenIdExists() {
    when(homizerStorageRepo.findById("456")).thenReturn(Optional.of(mockStorage));
    when(homizerStorageRepo.save(any(HomizerStorage.class))).thenReturn(mockStorage);

    homizerStorageService.saveHomizerStorage(mockStorageDto, mockUser);

    verify(homizerStorageRepo, times(1)).save(mockStorage);
    assertEquals("Test Storage", mockStorage.getName());
  }

  @Test
  void getAllHomizerStoragesfromUser_ShouldReturnList_WhenStoragesExist() {
    when(homizerStorageRepo.findByUserId(mockUser.getId())).thenReturn(List.of(mockStorage));

    List<HomizerStorageDto> result = homizerStorageService.getAllHomizerStoragesfromUser(mockUser);

    assertEquals(1, result.size());
    assertEquals("Test Storage", result.get(0).getName());
  }

  @Test
  void getAllHomizerStoragesfromUser_ShouldThrowException_WhenNoStoragesExist() {
    when(homizerStorageRepo.findByUserId(mockUser.getId())).thenReturn(List.of());

    Exception exception = assertThrows(EntityNotFoundException.class, () ->
            homizerStorageService.getAllHomizerStoragesfromUser(mockUser)
    );

    assertTrue(exception.getMessage().contains("No storage found for user:"));
  }

  @Test
  void getHomizerStorageById_ShouldReturnDto_WhenStorageExists() {
    when(homizerStorageRepo.findById("456")).thenReturn(Optional.of(mockStorage));

    HomizerStorageDto result = homizerStorageService.getHomizerStorageById("456");

    assertEquals("Test Storage", result.getName());
  }

  @Test
  void getHomizerStorageById_ShouldThrowException_WhenStorageNotFound() {
    when(homizerStorageRepo.findById("456")).thenReturn(Optional.empty());

    Exception exception = assertThrows(EntityNotFoundException.class, () ->
            homizerStorageService.getHomizerStorageById("456")
    );

    assertEquals("Storage with id: 456 not found!", exception.getMessage());
  }

  @Test
  void deleteHomizerStorageById_ShouldDelete_WhenStorageExists() {
    when(homizerStorageRepo.existsById("456")).thenReturn(true);

    homizerStorageService.deleteHomizerStorageById("456");

    verify(homizerStorageRepo, times(1)).deleteById("456");
  }

  @Test
  void deleteHomizerStorageById_ShouldThrowException_WhenStorageNotFound() {
    when(homizerStorageRepo.existsById("456")).thenReturn(false);

    Exception exception = assertThrows(EntityNotFoundException.class, () ->
            homizerStorageService.deleteHomizerStorageById("456")
    );

    assertEquals("Storage with id: 456 not found!", exception.getMessage());
  }

  @Test
  void getHomizerStorageForItem_ShouldReturnDto_WhenStorageExists() {
    when(homizerStorageRepo.findById("456")).thenReturn(Optional.of(mockStorage));

    HomizerStorageDto result = homizerStorageService.getHomizerStorageForItem("456");

    assertEquals("Test Storage", result.getName());
  }

  @Test
  void getHomizerStorageForItem_ShouldThrowException_WhenStorageNotFound() {
    when(homizerStorageRepo.findById("456")).thenReturn(Optional.empty());

    Exception exception = assertThrows(EntityNotFoundException.class, () ->
            homizerStorageService.getHomizerStorageForItem("456")
    );

    assertEquals("Storage with id: 456 not found!", exception.getMessage());
  }
}
