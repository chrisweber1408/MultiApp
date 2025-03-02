package com.example.MultiAppBackend.homizer.homizerStorage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomizerStorageRepo extends JpaRepository<HomizerStorage, String> {

    List<HomizerStorage> findByUserId(String userId);

}
