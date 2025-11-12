package com.israelgestaoos.apitechone.controller;

import com.israelgestaoos.apitechone.model.Role;
import com.israelgestaoos.apitechone.repository.RoleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")3
public class RoleController {

    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public List<Role> listar() {
        return roleRepository.findAll();
    }

    @PostMapping
    public Role criar(@RequestBody Role role) {
        return roleRepository.save(role);
    }
}
