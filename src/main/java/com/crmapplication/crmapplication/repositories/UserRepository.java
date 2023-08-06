package com.crmapplication.crmapplication.repositories;


import com.crmapplication.crmapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String Username, String email );

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);




}
