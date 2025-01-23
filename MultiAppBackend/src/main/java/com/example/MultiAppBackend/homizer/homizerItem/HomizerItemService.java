package com.example.MultiAppBackend.homizer.homizerItem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HomizerItemService {

  private final HomizerItemRepo homizerItemRepo;

  public void saveHomizerItem(HomizerItem homizerItem) {
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
