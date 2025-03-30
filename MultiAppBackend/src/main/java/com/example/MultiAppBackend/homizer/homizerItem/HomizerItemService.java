package com.example.MultiAppBackend.homizer.homizerItem;

import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorage;
import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorageRepo;
import com.example.MultiAppBackend.user.MyUser;
import com.example.MultiAppBackend.user.MyUserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomizerItemService {

  private final HomizerItemRepo homizerItemRepo;
  private final HomizerStorageRepo homizerStorageRepo;
  private final MyUserRepository myUserRepository;

  @Transactional
  public void saveHomizerItem(HomizerItemDto homizerItemDto, MyUser myUser) {
    HomizerItem homizerItem =
        homizerItemDto.getId() != null
            ? homizerItemRepo.findById(homizerItemDto.getId()).orElse(new HomizerItem())
            : new HomizerItem();

    homizerItem.setName(homizerItemDto.getName());
    homizerItem.setDescription(homizerItemDto.getDescription());
    homizerItem.setNumber(homizerItemDto.getNumber());
    homizerItem.setImage(homizerItemDto.getImage());
    homizerItem.setUser(myUser);

    if (homizerItemDto.getHomizerStorageId() != null) {
      HomizerStorage homizerStorage =
          homizerStorageRepo
              .findById(homizerItemDto.getHomizerStorageId())
              .orElseThrow(
                  () ->
                      new IllegalArgumentException(
                          "HomizerStorage with ID: "
                              + homizerItemDto.getHomizerStorageId()
                              + " for HomizerItem with ID: "
                              + homizerItem.getId()
                              + "not found!"));
      homizerItem.setHomizerStorage(homizerStorage);
    }

    homizerItemRepo.save(homizerItem);
    myUserRepository.save(myUser);
  }

  public List<HomizerItemDto> getAllHomizerItemsFromUser(MyUser myUser) {
    List<HomizerItem> homizerItems = homizerItemRepo.findByUserId(myUser.getId());

    if (homizerItems.isEmpty()) {
      throw new NoSuchElementException("No HomizerItems found for user with ID: " + myUser.getId());
    }

    return homizerItems.stream().map(this::createHomizerItemDto).toList();
  }

  public HomizerItemDto getHomizerItemById(String id) {
    return homizerItemRepo.findById(id)
            .map(this::convertToDto)
            .orElseThrow(() -> new EntityNotFoundException("Item with id: " + id + " not found!"));
  }

  private HomizerItemDto convertToDto(HomizerItem homizerItem) {
    HomizerItemDto dto = new HomizerItemDto();
    dto.setId(homizerItem.getId());
    dto.setName(homizerItem.getName());
    dto.setDescription(homizerItem.getDescription());
    dto.setImage(homizerItem.getImage());

    if (homizerItem.getHomizerStorage() != null) {
      dto.setHomizerStorageId(homizerItem.getHomizerStorage().getId());
    }
    return dto;
  }


  @Transactional
  public void deleteHomizerItemById(String id) {
    HomizerItem homizerItem =
        homizerItemRepo
            .findById(id)
            .orElseThrow(() -> new NoSuchElementException("Item with id: " + id + " not found!"));

    homizerItemRepo.delete(homizerItem);
  }

  public List<HomizerItemDto> getAllHomizerItemsInStorage(String id) {
    List<HomizerItem> homizerItemsInStorage = homizerItemRepo.findByHomizerStorageId(id);
    if (homizerItemsInStorage.isEmpty()) {
      throw new NoSuchElementException("No HomizerItems found for storage with id: " + id);
    }
    return homizerItemsInStorage.stream()
        .map(this::createHomizerItemDto)
        .collect(Collectors.toList());
  }

  private HomizerItemDto createHomizerItemDto(HomizerItem homizerItem) {
    HomizerItemDto homizerItemDto = new HomizerItemDto();
    homizerItemDto.setId(homizerItem.getId());
    homizerItemDto.setName(homizerItem.getName());
    homizerItemDto.setDescription(homizerItem.getDescription());
    homizerItemDto.setNumber(homizerItem.getNumber());
    homizerItemDto.setImage(homizerItem.getImage());

    Optional.ofNullable(homizerItem.getHomizerStorage())
        .ifPresent(
            homizerStorage -> homizerItemDto.setHomizerStorageName(homizerStorage.getName()));

    return homizerItemDto;
  }
}
