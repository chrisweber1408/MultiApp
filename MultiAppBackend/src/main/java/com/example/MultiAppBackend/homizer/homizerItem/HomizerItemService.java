package com.example.MultiAppBackend.homizer.homizerItem;

import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorage;
import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorageRepo;
import com.example.MultiAppBackend.user.MyUser;
import com.example.MultiAppBackend.user.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HomizerItemService {

  private final HomizerItemRepo homizerItemRepo;
  private final HomizerStorageRepo homizerStorageRepo;
  private final MyUserRepository myUserRepository;

  public void saveHomizerItem(HomizerItemDto homizerItemDto, MyUser myUser) {
    HomizerItem homizerItem = new HomizerItem();
    if (null != homizerItemDto.getHomizerStorageId()) {
      Optional<HomizerStorage> homizerStorage =
          homizerStorageRepo.findById(homizerItemDto.getHomizerStorageId());
        homizerStorage.ifPresent(homizerItem::setHomizerStorage);
    }
    homizerItem.setName(homizerItemDto.getName());
    homizerItem.setNumber(homizerItemDto.getNumber());
    homizerItem.setImage(homizerItemDto.getImage());
    homizerItem.setDescription(homizerItemDto.getDescription());
    homizerItem.setUser(myUser);
    myUser.addHomizerItem(homizerItem);
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

  public HomizerItem getHomizerItemById(String id) {
    Optional<HomizerItem> storageItem = homizerItemRepo.findById(id);
    if (storageItem.isPresent()) {
      return storageItem.get();
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
