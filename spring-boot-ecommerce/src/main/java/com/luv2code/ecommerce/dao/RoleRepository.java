package com.luv2code.ecommerce.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.ecommerce.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}