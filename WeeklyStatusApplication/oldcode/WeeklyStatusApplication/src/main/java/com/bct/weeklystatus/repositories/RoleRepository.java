package com.bct.weeklystatus.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bct.weeklystatus.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}