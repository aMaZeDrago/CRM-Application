package com.crmapplication.crmapplication.repositories;

import com.crmapplication.crmapplication.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String  name);
}
