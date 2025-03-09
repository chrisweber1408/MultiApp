package com.example.MultiAppBackend.homizer.homizerItem;

import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorage;
import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorageRepo;
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
class HomizerItemServiceTest {

  @Mock
  private HomizerItemRepo homizerItemRepo;

  @Mock
  private HomizerStorageRepo homizerStorageRepo;

  @Mock
  private MyUserRepository myUserRepository;

  @InjectMocks
  private HomizerItemService homizerItemService;

  private MyUser myUser;
  private HomizerStorage homizerStorage;
  private HomizerItem homizerItem;
  private HomizerItemDto homizerItemDto;

  @BeforeEach
  void setUp() {
    myUser = new MyUser();

    homizerStorage = new HomizerStorage();

    homizerItem = new HomizerItem();
    homizerItem.setName("Test Item");
    homizerItem.setUser(myUser);
    homizerItem.setHomizerStorage(homizerStorage);

    homizerItemDto = new HomizerItemDto();
    homizerItemDto.setName("Updated Item");
    homizerItemDto.setNumber(5);
    homizerItemDto.setImage("image.png");
    homizerItemDto.setDescription("Updated Description");
    homizerItemDto.setHomizerStorageId("storage123");
  }

  @Test
  void shouldSaveNewHomizerItem() {
    when(homizerStorageRepo.findById("storage123")).thenReturn(Optional.of(homizerStorage));
    when(homizerItemRepo.save(any(HomizerItem.class))).thenReturn(homizerItem);
    when(myUserRepository.save(any(MyUser.class))).thenReturn(myUser);

    homizerItemService.saveHomizerItem(homizerItemDto, myUser);

    verify(homizerItemRepo, times(1)).save(any(HomizerItem.class));
    verify(myUserRepository, times(1)).save(any(MyUser.class));
  }

  @Test
  void shouldSaveHomizerItemWithoutStorage() {
    homizerItemDto.setHomizerStorageId(null);

    when(homizerItemRepo.save(any(HomizerItem.class))).thenReturn(homizerItem);
    when(myUserRepository.save(any(MyUser.class))).thenReturn(myUser);

    homizerItemService.saveHomizerItem(homizerItemDto, myUser);

    verify(homizerItemRepo, times(1)).save(any(HomizerItem.class));
    verify(myUserRepository, times(1)).save(any(MyUser.class));
  }

  @Test
  void shouldReturnAllHomizerItemsFromUser() {
    when(homizerItemRepo.findByUserId(myUser.getId())).thenReturn(List.of(homizerItem));

    List<HomizerItem> result = homizerItemService.getAllHomizerItemsFromUser(myUser);

    assertFalse(result.isEmpty());
    assertEquals(1, result.size());
    assertEquals("Test Item", result.get(0).getName());
  }

  @Test
  void shouldThrowExceptionWhenUserHasNoItems() {
    when(homizerItemRepo.findByUserId(myUser.getId())).thenReturn(List.of());

    assertThrows(NoSuchElementException.class, () -> homizerItemService.getAllHomizerItemsFromUser(myUser));
  }

  @Test
  void shouldFindHomizerItemById() {
    when(homizerItemRepo.findById("item123")).thenReturn(Optional.of(homizerItem));

    HomizerItem result = homizerItemService.getHomizerItemById("item123");

    assertNotNull(result);
    assertEquals("Test Item", result.getName());
  }

  @Test
  void shouldThrowExceptionWhenHomizerItemNotFound() {
    when(homizerItemRepo.findById("invalid-id")).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> homizerItemService.getHomizerItemById("invalid-id"));
  }

  @Test
  void shouldDeleteHomizerItemById() {
    when(homizerItemRepo.findById("item123")).thenReturn(Optional.of(homizerItem));
    doNothing().when(homizerItemRepo).deleteById("item123");

    homizerItemService.deleteHomizerItemById("item123");

    verify(homizerItemRepo, times(1)).deleteById("item123");
  }

  @Test
  void shouldThrowExceptionWhenDeletingNonExistentHomizerItem() {
    when(homizerItemRepo.findById("invalid-id")).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> homizerItemService.deleteHomizerItemById("invalid-id"));
  }
}
