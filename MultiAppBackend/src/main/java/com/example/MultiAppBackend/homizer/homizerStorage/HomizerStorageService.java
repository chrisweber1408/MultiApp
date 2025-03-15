package com.example.MultiAppBackend.homizer.homizerStorage;

import com.example.MultiAppBackend.homizer.homizerItem.HomizerItem;
import com.example.MultiAppBackend.homizer.homizerItem.HomizerItemDto;
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

  public List<HomizerStorageDto> getAllHomizerStoragesfromUser(MyUser myUser) {
    List<HomizerStorage> homizerStorages = homizerStorageRepo.findByUserId(myUser.getId());
    if (!homizerStorages.isEmpty()) {
      List<HomizerStorageDto> allHomizerItemDtoFromUser = new java.util.ArrayList<>(List.of());
      for (HomizerStorage homizerStorage : homizerStorages) {
        HomizerStorageDto homizerStorageDto = createHomizerStorageDto(homizerStorage);
        allHomizerItemDtoFromUser.add(homizerStorageDto);
      }
      return allHomizerItemDtoFromUser;
    } else {
      throw new NoSuchElementException("List is empty");
    }
  }

  public HomizerStorageDto getHomizerStorageById(String id) {
    Optional<HomizerStorage> optionalHomizerStorage = homizerStorageRepo.findById(id);
    if (optionalHomizerStorage.isPresent()) {
      HomizerStorage homizerStorage = optionalHomizerStorage.get();
      HomizerStorageDto homizerStorageDto = new HomizerStorageDto();
      homizerStorageDto.setId(homizerStorage.getId());
      homizerStorageDto.setName(homizerStorage.getName());
      homizerStorageDto.setDescription(homizerStorage.getDescription());
      homizerStorageDto.setImage(homizerStorage.getImage());
      return homizerStorageDto;
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

  private static HomizerStorageDto createHomizerStorageDto(HomizerStorage homizerStorage) {
    HomizerStorageDto homizerStorageDto = new HomizerStorageDto();
    homizerStorageDto.setId(homizerStorage.getId());
    homizerStorageDto.setName(homizerStorage.getName());
    homizerStorageDto.setDescription(homizerStorage.getDescription());
    homizerStorageDto.setImage(homizerStorage.getImage());
    return homizerStorageDto;
  }

}
