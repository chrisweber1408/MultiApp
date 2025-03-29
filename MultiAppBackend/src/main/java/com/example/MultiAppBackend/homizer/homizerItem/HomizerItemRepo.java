package com.example.MultiAppBackend.homizer.homizerItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomizerItemRepo extends JpaRepository<HomizerItem, String> {

  List<HomizerItem> findByUserId(String userId);

  List<HomizerItem> findByHomizerStorageId(String homizerStrorageId);
}
