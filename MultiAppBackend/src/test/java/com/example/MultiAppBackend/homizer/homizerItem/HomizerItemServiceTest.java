package com.example.MultiAppBackend.homizer.homizerItem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorage;
import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorageRepo;
import com.example.MultiAppBackend.user.MyUser;
import com.example.MultiAppBackend.user.MyUserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class HomizerItemServiceTest {

  @Mock
  private HomizerItemRepo homizerItemRepo;

  @Mock
  private HomizerStorageRepo homizerStorageRepo;

  @Mock
  private MyUserRepository myUserRepository;

  @InjectMocks
  private HomizerItemService homizerItemService;

  private HomizerItem homizerItem;
  private MyUser user;
  private HomizerStorage storage;
  private HomizerItemDto homizerItemDto;

  @BeforeEach
  void setUp() {
    user = new MyUser();

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

    homizerItemService.saveHomizerItem(homizerItemDto, user);

    verify(homizerItemRepo, times(1)).save(any(HomizerItem.class));
    verify(myUserRepository, times(1)).save(user);
  }

  @Test
  void getAllHomizerItemsFromUser_shouldReturnItems() {
    when(homizerItemRepo.findByUserId(user.getId())).thenReturn(List.of(homizerItem));

    List<HomizerItemDto> items = homizerItemService.getAllHomizerItemsFromUser(user);

    assertEquals(1, items.size());
    assertEquals("Item Name", items.get(0).getName());
  }

  @Test
  void getAllHomizerItemsFromUser_shouldThrowExceptionIfNoItemsFound() {
    when(homizerItemRepo.findByUserId(user.getId())).thenReturn(List.of());

    assertThrows(NoSuchElementException.class, () -> homizerItemService.getAllHomizerItemsFromUser(user));
  }

  @Test
  void getHomizerItemById_shouldReturnItem() {
    when(homizerItemRepo.findById("item123")).thenReturn(Optional.of(homizerItem));

    HomizerItemDto result = homizerItemService.getHomizerItemById("item123");

    assertEquals("Item Name", result.getName());
  }

  @Test
  void getHomizerItemById_shouldThrowExceptionIfNotFound() {
    when(homizerItemRepo.findById("item123")).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> homizerItemService.getHomizerItemById("item123"));
  }

  @Test
  @Transactional
  void deleteHomizerItemById_shouldDeleteItem() {
    when(homizerItemRepo.findById("item123")).thenReturn(Optional.of(homizerItem));

    homizerItemService.deleteHomizerItemById("item123");

    verify(homizerItemRepo, times(1)).delete(homizerItem);
  }

  @Test
  void deleteHomizerItemById_shouldThrowExceptionIfNotFound() {
    when(homizerItemRepo.findById("item123")).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> homizerItemService.deleteHomizerItemById("item123"));
  }

  @Test
  void getAllHomizerItemsInStorage_shouldReturnItems() {
    when(homizerItemRepo.findByHomizerStorageId("storage123")).thenReturn(List.of(homizerItem));

    List<HomizerItemDto> items = homizerItemService.getAllHomizerItemsInStorage("storage123");

    assertEquals(1, items.size());
    assertEquals("Item Name", items.get(0).getName());
  }

  @Test
  void getAllHomizerItemsInStorage_shouldThrowExceptionIfNoItemsFound() {
    when(homizerItemRepo.findByHomizerStorageId("storage123")).thenReturn(List.of());

    assertThrows(NoSuchElementException.class, () -> homizerItemService.getAllHomizerItemsInStorage("storage123"));
  }

}
