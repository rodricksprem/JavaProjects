package com.bct.weeklystatus.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bct.weeklystatus.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> ,UserRepositoryCustom  {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    
}