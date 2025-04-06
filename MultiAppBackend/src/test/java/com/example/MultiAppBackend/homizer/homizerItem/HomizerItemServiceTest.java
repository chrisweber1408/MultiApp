package com.example.MultiAppBackend.homizer.homizerItem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorage;
import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorageRepo;
import com.example.MultiAppBackend.user.MyUser;
import com.example.MultiAppBackend.user.MyUserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HomizerItemServiceTest {

  @Mock private HomizerItemRepo homizerItemRepo;

  @Mock private HomizerStorageRepo homizerStorageRepo;

  @Mock private MyUserRepository myUserRepository;

  @InjectMocks private HomizerItemService homizerItemService;

  private HomizerItem homizerItem;
  private MyUser user;
  private HomizerStorage storage;
  private HomizerItemDto homizerItemDto;
  private Principal principal;

  @BeforeEach
  void setUp() {
    user = new MyUser();
    user.setEmail("testUser");

    principal = () -> "testUser";

    storage = new HomizerStorage();

    homizerItem = new HomizerItem();
    homizerItem.setName("Item Name");
    homizerItem.setUser(user);
    homizerItem.setHomizerStorage(storage);

    homizerItemDto = new HomizerItemDto();
    homizerItemDto.setId("item123");
    homizerItemDto.setName("Item Name");
    homizerItemDto.setHomizerStorageId("storage123");
  }

  @Test
  void saveHomizerItem_shouldSaveNewItem() {
    when(homizerStorageRepo.findById("storage123")).thenReturn(Optional.of(storage));
    when(homizerItemRepo.findById("item123")).thenReturn(Optional.empty());
    when(myUserRepository.findByEmail("testUser")).thenReturn(Optional.of(user));

    homizerItemService.saveHomizerItem(homizerItemDto, principal);

    verify(homizerItemRepo, times(1)).save(any(HomizerItem.class));
    verify(myUserRepository, times(1)).save(user);
  }

  @Test
  void getAllHomizerItemsFromUser_shouldReturnItems() {
    when(homizerItemRepo.findByUserId(user.getId())).thenReturn(List.of(homizerItem));
    when(myUserRepository.findByEmail("testUser")).thenReturn(Optional.of(user));

    List<HomizerItemDto> items = homizerItemService.getAllHomizerItemsFromUser(principal);

    assertEquals(1, items.size());
    assertEquals("Item Name", items.get(0).getName());
  }

  @Test
  void getAllHomizerItemsFromUser_shouldThrowExceptionIfNoItemsFound() {
    when(homizerItemRepo.findByUserId(user.getId())).thenReturn(List.of());
    when(myUserRepository.findByEmail("testUser")).thenReturn(Optional.of(user));

    assertThrows(
        EntityNotFoundException.class,
        () -> homizerItemService.getAllHomizerItemsFromUser(principal));
  }

  @Test
  void getHomizerItemById_shouldReturnItem() {
    when(homizerItemRepo.findByIdAndUserId("item123", user.getId()))
        .thenReturn(Optional.of(homizerItem));
    when(myUserRepository.findByEmail("testUser")).thenReturn(Optional.of(user));

    HomizerItemDto result = homizerItemService.getHomizerItemById("item123", principal);

    assertEquals("Item Name", result.getName());
  }

  @Test
  void getHomizerItemById_shouldThrowExceptionIfNotFound() {
    when(homizerItemRepo.findByIdAndUserId("item123", user.getId())).thenReturn(Optional.empty());
    when(myUserRepository.findByEmail("testUser")).thenReturn(Optional.of(user));

    assertThrows(
        EntityNotFoundException.class,
        () -> homizerItemService.getHomizerItemById("item123", principal));
  }

  @Test
  @Transactional
  void deleteHomizerItemById_shouldDeleteItem() {
    when(homizerItemRepo.findByIdAndUserId("item123", user.getId()))
        .thenReturn(Optional.of(homizerItem));
    when(myUserRepository.findByEmail("testUser")).thenReturn(Optional.of(user));

    homizerItemService.deleteHomizerItemById("item123", principal);

    verify(homizerItemRepo, times(1)).delete(homizerItem);
  }

  @Test
  void deleteHomizerItemById_shouldThrowExceptionIfNotFound() {
    when(homizerItemRepo.findByIdAndUserId("item123", user.getId())).thenReturn(Optional.empty());
    when(myUserRepository.findByEmail("testUser")).thenReturn(Optional.of(user));

    assertThrows(
        EntityNotFoundException.class,
        () -> homizerItemService.deleteHomizerItemById("item123", principal));
  }

  @Test
  void getAllHomizerItemsInStorage_shouldReturnItems() {
    when(homizerItemRepo.findByHomizerStorageIdAndUserId("storage123", user.getId()))
        .thenReturn(List.of(homizerItem));
    when(myUserRepository.findByEmail("testUser")).thenReturn(Optional.of(user));

    List<HomizerItemDto> items =
        homizerItemService.getAllHomizerItemsInStorage("storage123", principal);

    assertEquals(1, items.size());
    assertEquals("Item Name", items.get(0).getName());
  }

  @Test
  void getAllHomizerItemsInStorage_shouldThrowExceptionIfNoItemsFound() {
    when(homizerItemRepo.findByHomizerStorageIdAndUserId("storage123", user.getId()))
        .thenReturn(List.of());
    when(myUserRepository.findByEmail("testUser")).thenReturn(Optional.of(user));

    assertThrows(
        EntityNotFoundException.class,
        () -> homizerItemService.getAllHomizerItemsInStorage("storage123", principal));
  }
}
