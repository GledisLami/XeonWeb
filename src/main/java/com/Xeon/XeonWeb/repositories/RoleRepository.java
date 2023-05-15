package com.Xeon.XeonWeb.repositories;

import com.Xeon.XeonWeb.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRole(String role);

    Optional<Role> findById(Integer id);
}
