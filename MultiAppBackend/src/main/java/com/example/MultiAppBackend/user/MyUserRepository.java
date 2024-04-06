package com.example.MultiAppBackend.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, String> {

    Optional<MyUser> findByUsername(String username);


}
