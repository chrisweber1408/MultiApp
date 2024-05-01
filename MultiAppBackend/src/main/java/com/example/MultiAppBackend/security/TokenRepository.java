package com.example.MultiAppBackend.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface TokenRepository extends JpaRepository<Token, Integer> {


    @Query("""
select t from Token t inner join MyUser u on t.myUser.id = u.id
where t.myUser.id = :myUserId and t.loggedOut = false
""")
    List<Token> findAllTokensByUser(String myUserId);

    Optional<Token> findByToken(String token);
}

