package com.israelgestaoos.apitechone.repository;

import com.israelgestaoos.apitechone.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNome(String nome);
}
