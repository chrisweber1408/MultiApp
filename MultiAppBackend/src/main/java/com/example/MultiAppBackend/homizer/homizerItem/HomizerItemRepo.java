package com.example.MultiAppBackend.homizer.homizerItem;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomizerItemRepo extends JpaRepository<HomizerItem, String> {

  List<HomizerItem> findByUserId(String userId);

  List<HomizerItem> findByHomizerStorageIdAndUserId(String homizerStrorageId, String userId);

  Optional<HomizerItem> findByIdAndUserId(String Id, String userId);
}
