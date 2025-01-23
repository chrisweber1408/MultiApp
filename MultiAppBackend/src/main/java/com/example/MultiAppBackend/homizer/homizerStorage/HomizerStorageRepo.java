package com.example.MultiAppBackend.homizer.homizerStorage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomizerStorageRepo extends JpaRepository<HomizerStorage, String> {}
