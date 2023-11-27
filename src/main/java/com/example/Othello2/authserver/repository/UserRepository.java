package com.example.Othello2.authserver.repository;

import com.example.Othello2.authserver.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    Boolean existsByUsername(String username);
}
