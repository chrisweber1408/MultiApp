package com.example.MultiAppBackend.homizer.homizerStorage;

import com.example.MultiAppBackend.user.MyUser;
import com.example.MultiAppBackend.user.MyUserRepository;
import jakarta.persistence.EntityNotFoundException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class HomizerStorageService {

  private final HomizerStorageRepo homizerStorageRepo;
  private final MyUserRepository myUserRepository;

  public void saveHomizerStorage(HomizerStorageDto homizerStorageDto, Principal principal) {
    MyUser myUser = findUser(principal);
    HomizerStorage homizerStorage =
        homizerStorageDto.getId() != null
            ? homizerStorageRepo
                .findByIdAndUserId(homizerStorageDto.getId(), myUser.getId())
                .orElse(new HomizerStorage())
            : new HomizerStorage();

    updateHomizerStorageFromDto(homizerStorage, homizerStorageDto, myUser);

    homizerStorageRepo.save(homizerStorage);
  }

  public List<HomizerStorageDto> getAllHomizerStoragesfromUser(Principal principal) {
    MyUser myUser = findUser(principal);
    List<HomizerStorage> homizerStorages = homizerStorageRepo.findByUserId(myUser.getId());
    if (homizerStorages.isEmpty()) {
      throw new EntityNotFoundException("No storage found for user: " + myUser.getId());
    }
    return homizerStorages.stream().map(this::createHomizerStorageDto).toList();
  }

  public HomizerStorageDto getHomizerStorageById(String id, Principal principal) {
    MyUser myUser = findUser(principal);
    return homizerStorageRepo
        .findByIdAndUserId(id, myUser.getId())
        .map(this::createHomizerStorageDto)
        .orElseThrow(() -> new EntityNotFoundException("Storage with id: " + id + " not found!"));
  }

  public void deleteHomizerStorageById(String id, Principal principal) {
    MyUser myUser = findUser(principal);
    if (!homizerStorageRepo.existsByIdAndUserId(id, myUser.getId())) {
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

  private MyUser findUser(Principal principal) {
    return myUserRepository
        .findByEmail(principal.getName())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
  }
}
