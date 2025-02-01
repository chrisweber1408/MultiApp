package com.example.MultiAppBackend.homizer.homizerItem;

import com.example.MultiAppBackend.homizer.dto.HomizerItemDto;
import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorage;
import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorageRepo;
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

  public void saveHomizerItem(HomizerItemDto homizerItemDto) {
    HomizerItem homizerItem = new HomizerItem();
    if (null != homizerItemDto.getHomizerStorageId()) {
      Optional<HomizerStorage> homizerStorage =
          homizerStorageRepo.findById(homizerItemDto.getHomizerStorageId());
      if (homizerStorage.isPresent()) {
        homizerItem.setHomizerStorage(homizerStorage.get());
      }
    }
    homizerItem.setName(homizerItemDto.getName());
    homizerItem.setNumber(homizerItemDto.getNumber());
    homizerItem.setImage(homizerItemDto.getImage());
    homizerItem.setDescription(homizerItemDto.getDescription());
    homizerItemRepo.save(homizerItem);
  }

  public List<HomizerItem> getAllHomizerItems() {
    List<HomizerItem> homizerItems = homizerItemRepo.findAll();
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
