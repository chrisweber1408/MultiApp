package com.example.MultiAppBackend.homizer.homizerItem;

import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorage;
import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorageRepo;
import com.example.MultiAppBackend.user.MyUser;
import com.example.MultiAppBackend.user.MyUserRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomizerItemService {

  private final HomizerItemRepo homizerItemRepo;
  private final HomizerStorageRepo homizerStorageRepo;
  private final MyUserRepository myUserRepository;

  public void saveHomizerItem(HomizerItemDto homizerItemDto, MyUser myUser) {
    HomizerItem homizerItem = new HomizerItem();
    if (null != homizerItemDto.getId()) {
      Optional<HomizerItem> optionalHomizerItem = homizerItemRepo.findById(homizerItemDto.getId());
      if (optionalHomizerItem.isPresent()) {
        homizerItem = optionalHomizerItem.get();
      }
    }
    homizerItem.setName(homizerItemDto.getName());
    homizerItem.setDescription(homizerItemDto.getDescription());
    homizerItem.setNumber(homizerItemDto.getNumber());
    homizerItem.setImage(homizerItemDto.getImage());
    homizerItem.setUser(myUser);
    if (null != homizerItemDto.getHomizerStorageId()) {
      Optional<HomizerStorage> optionalHomizerStorage =
          homizerStorageRepo.findById(homizerItemDto.getHomizerStorageId());
      if (optionalHomizerStorage.isPresent()) {
        homizerItem.setHomizerStorage(optionalHomizerStorage.get());
      }
    }
    homizerItemRepo.save(homizerItem);
    myUserRepository.save(myUser);
  }

  public List<HomizerItem> getAllHomizerItemsFromUser(MyUser myUser) {
    List<HomizerItem> homizerItems = homizerItemRepo.findByUserId(myUser.getId());
    if (!homizerItems.isEmpty()) {
      return homizerItems;
    } else {
      throw new NoSuchElementException("List is empty");
    }
  }

  public HomizerItemDto getHomizerItemById(String id) {
    Optional<HomizerItem> optionalHomizerItem = homizerItemRepo.findById(id);
    if (optionalHomizerItem.isPresent()) {
      HomizerItemDto homizerItemDto = new HomizerItemDto();
      HomizerItem homizerItem = optionalHomizerItem.get();
      homizerItemDto.setName(homizerItem.getName());
      homizerItemDto.setDescription(homizerItem.getDescription());
      homizerItemDto.setImage(homizerItem.getImage());
      homizerItemDto.setId(homizerItem.getId());
      if (null != homizerItem.getHomizerStorage()) {
        homizerItemDto.setHomizerStorageId(homizerItem.getHomizerStorage().getId());
      }
      return homizerItemDto;
    } else {
      throw new NoSuchElementException("Item with id: " + id + " not found!");
    }
  }

  public void deleteHomizerItemById(String id) {
    Optional<HomizerItem> storageItem = homizerItemRepo.findById(id);
    if (storageItem.isPresent()) {
      homizerItemRepo.deleteById(id);
    } else {
      throw new NoSuchElementException("Item with id: " + id + " not found!");
    }
  }
}
