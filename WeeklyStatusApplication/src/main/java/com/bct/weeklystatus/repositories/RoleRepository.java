package com.bct.weeklystatus.repositories;

import org.springframework.stereotype.Repository;

import com.bct.weeklystatus.entities.Role;
import com.bct.weeklystatus.entities.RoleName;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}