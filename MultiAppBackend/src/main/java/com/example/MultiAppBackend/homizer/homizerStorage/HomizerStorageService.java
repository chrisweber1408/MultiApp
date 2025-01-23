package com.example.MultiAppBackend.homizer.homizerStorage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HomizerStorageService {

  private final HomizerStorageRepo homizerStorageRepo;

  public void saveHomizerStorage(HomizerStorage homizerStorage) {
    homizerStorageRepo.save(homizerStorage);
  }

  public List<HomizerStorage> getAllHomizerStorages() {
    List<HomizerStorage> homizerStorages = homizerStorageRepo.findAll();
    if (!homizerStorages.isEmpty()) {
      return homizerStorages;
    } else {
      throw new NoSuchElementException("List is empty");
    }
  }

  public HomizerStorage getHomizerStorageById(String id) {
    Optional<HomizerStorage> homizerStorage = homizerStorageRepo.findById(id);
    if (homizerStorage.isPresent()) {
      return homizerStorage.get();
    } else {
      throw new NoSuchElementException("Storage with id: " + id + " not found!");
    }
  }

  public void deleteHomizerStorageById(String id) {
    Optional<HomizerStorage> homizerStorage = homizerStorageRepo.findById(id);
    if (homizerStorage.isPresent()) {
      homizerStorageRepo.deleteById(id);
    } else {
      throw new NoSuchElementException("Storage with id: " + id + " not found!");
    }
  }
}
