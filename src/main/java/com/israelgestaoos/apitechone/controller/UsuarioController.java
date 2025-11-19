package com.israelgestaoos.apitechone.controller;

import com.israelgestaoos.apitechone.dto.usuario.UsuarioRequest;
import com.israelgestaoos.apitechone.dto.usuario.UsuarioResponse;
import com.israelgestaoos.apitechone.dto.DtoMapper;
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
    private final DtoMapper mapper;

    public UsuarioController(
            UsuarioService usuarioService,
            RoleRepository roleRepository,
            DtoMapper mapper
    ) {
        this.usuarioService = usuarioService;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @GetMapping
    public List<UsuarioResponse> listar() {
        return usuarioService.listarTodos().stream()
                .map(mapper::toUsuarioResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public UsuarioResponse buscar(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(mapper::toUsuarioResponse)
                .orElse(null);
    }

    @PostMapping
    public UsuarioResponse criar(@RequestBody UsuarioRequest dto) {

        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role não encontrada"));

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setRole(role);

        Usuario salvo = usuarioService.salvar(usuario);
        return mapper.toUsuarioResponse(salvo);
    }

    @PutMapping("/{id}")
    public UsuarioResponse atualizar(@PathVariable Long id, @RequestBody UsuarioRequest dto) {

        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role não encontrada"));

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setRole(role);

        Usuario salvo = usuarioService.salvar(usuario);
        return mapper.toUsuarioResponse(salvo);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
    }
}
