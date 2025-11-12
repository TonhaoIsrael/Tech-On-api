package com.israelgestaoos.apitechone.repository;

import com.israelgestaoos.apitechone.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> { }
