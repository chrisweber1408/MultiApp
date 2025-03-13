package com.example.MultiAppBackend.homizer.homizerStorage;

import com.example.MultiAppBackend.user.MyUser;
import com.example.MultiAppBackend.user.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HomizerStorageService {

  private final HomizerStorageRepo homizerStorageRepo;
  private final MyUserRepository myUserRepository;

  public void saveHomizerStorage(HomizerStorageDto homizerStorageDto, MyUser myUser) {
    HomizerStorage homizerStorage = new HomizerStorage();
    if (null != homizerStorageDto.getId()){
      Optional<HomizerStorage> optionalHomizerStorage = homizerStorageRepo.findById(homizerStorageDto.getId());
      if (optionalHomizerStorage.isPresent()) {
        homizerStorage = optionalHomizerStorage.get();
        homizerStorage.setName(homizerStorageDto.getName());
        homizerStorage.setImage(homizerStorageDto.getImage());
        homizerStorage.setUser(myUser);
        homizerStorage.setDescription(homizerStorageDto.getDescription());
      }
    } else  {
      homizerStorage.setName(homizerStorageDto.getName());
      homizerStorage.setImage(homizerStorageDto.getImage());
      homizerStorage.setUser(myUser);
      homizerStorage.setDescription(homizerStorageDto.getDescription());
    }
    myUserRepository.save(myUser);
    homizerStorageRepo.save(homizerStorage);
  }

  public List<HomizerStorage> getAllHomizerStoragesfromUser(MyUser myUser) {
    List<HomizerStorage> homizerStorages = homizerStorageRepo.findByUserId(myUser.getId());
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
