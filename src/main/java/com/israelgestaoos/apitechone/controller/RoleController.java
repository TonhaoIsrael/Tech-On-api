package com.israelgestaoos.apitechone.controller;

import com.israelgestaoos.apitechone.model.Role;
import com.israelgestaoos.apitechone.repository.RoleRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/roles") @CrossOrigin(origins="*")
public class RoleController {
    private final RoleRepository repo;
    public RoleController(RoleRepository repo){ this.repo = repo; }

    @GetMapping public List<Role> listar(){ return repo.findAll(); }

    @PostMapping(consumes="application/json", produces="application/json")
    public Role criar(@RequestBody Role role){
        if(role==null || role.getNome()==null || role.getNome().isBlank())
            throw new IllegalArgumentException("nome é obrigatório");
        return repo.save(role);
    }
}
