package com.example.MultiAppBackend.homizer.homizerStorage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HomizerStorageRepo extends JpaRepository<HomizerStorage, String> {

    List<HomizerStorage> findByUserId(String userId);

    Optional<HomizerStorage> findByIdAndUserId(String id, String userId);

    boolean existsByIdAndUserId(String id, String userId);

}
