package com.example.MultiAppBackend.homizer.homizerItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomizerItemRepo extends JpaRepository<HomizerItem, String> {
}
