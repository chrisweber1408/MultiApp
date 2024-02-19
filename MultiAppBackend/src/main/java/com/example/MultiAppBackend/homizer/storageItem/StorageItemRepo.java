package com.example.MultiAppBackend.homizer.storageItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageItemRepo extends JpaRepository<StorageItem, String> {
}
