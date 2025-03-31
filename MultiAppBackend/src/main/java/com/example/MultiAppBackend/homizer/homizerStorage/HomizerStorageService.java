package com.example.MultiAppBackend.homizer.homizerStorage;

import com.example.MultiAppBackend.user.MyUser;
import com.example.MultiAppBackend.user.MyUserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomizerStorageService {

  private final HomizerStorageRepo homizerStorageRepo;
  private final MyUserRepository myUserRepository;

  public void saveHomizerStorage(HomizerStorageDto homizerStorageDto, MyUser myUser) {
    HomizerStorage homizerStorage =
        homizerStorageDto.getId() != null
            ? homizerStorageRepo.findById(homizerStorageDto.getId()).orElse(new HomizerStorage())
            : new HomizerStorage();

    updateHomizerStorageFromDto(homizerStorage, homizerStorageDto, myUser);

    homizerStorageRepo.save(homizerStorage);
  }

  public List<HomizerStorageDto> getAllHomizerStoragesfromUser(MyUser myUser) {
    List<HomizerStorage> homizerStorages = homizerStorageRepo.findByUserId(myUser.getId());
    if (homizerStorages.isEmpty()) {
      throw new EntityNotFoundException("No storage found for user: " + myUser.getId());
    }
    return homizerStorages.stream().map(this::createHomizerStorageDto).toList();
  }

  public HomizerStorageDto getHomizerStorageById(String id) {
    return homizerStorageRepo
        .findById(id)
        .map(this::createHomizerStorageDto)
        .orElseThrow(() -> new EntityNotFoundException("Storage with id: " + id + " not found!"));
  }

  public void deleteHomizerStorageById(String id) {
    if (!homizerStorageRepo.existsById(id)) {
      throw new EntityNotFoundException("Storage with id: " + id + " not found!");
    }
    homizerStorageRepo.deleteById(id);
  }

  public HomizerStorageDto getHomizerStorageForItem(String id) {
    return homizerStorageRepo
        .findById(id)
        .map(this::createHomizerStorageDto)
        .orElseThrow(() -> new EntityNotFoundException("Storage with id: " + id + " not found!"));
  }

  private void updateHomizerStorageFromDto(
      HomizerStorage storage, HomizerStorageDto dto, MyUser user) {
    storage.setName(dto.getName());
    storage.setImage(dto.getImage());
    storage.setUser(user);
    storage.setDescription(dto.getDescription());
  }

  private HomizerStorageDto createHomizerStorageDto(HomizerStorage homizerStorage) {
    return new HomizerStorageDto(
        homizerStorage.getId(),
        homizerStorage.getName(),
        homizerStorage.getDescription(),
        homizerStorage.getImage());
  }
}
