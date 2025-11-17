package com.israelgestaoos.apitechone.controller;

import com.israelgestaoos.apitechone.dto.UsuarioRequest;
import com.israelgestaoos.apitechone.model.Role;
import com.israelgestaoos.apitechone.model.Usuario;
import com.israelgestaoos.apitechone.repository.RoleRepository;
import com.israelgestaoos.apitechone.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RoleRepository roleRepository;

    public UsuarioController(UsuarioService usuarioService, RoleRepository roleRepository) {
        this.usuarioService = usuarioService;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id).orElse(null);
    }

    @PostMapping
    public Usuario criar(@RequestBody UsuarioRequest dto) {

        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role não encontrada"));

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setRole(role);

        return usuarioService.salvar(usuario);
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody UsuarioRequest dto) {

        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role não encontrada"));

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setRole(role);

        return usuarioService.salvar(usuario);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
    }
}
