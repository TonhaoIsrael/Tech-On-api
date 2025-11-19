package com.israelgestaoos.apitechone.auth;

import com.israelgestaoos.apitechone.dto.auth.LoginRequest;
import com.israelgestaoos.apitechone.dto.auth.LoginResponse;
import com.israelgestaoos.apitechone.model.Usuario;
import com.israelgestaoos.apitechone.repository.UsuarioRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // LOGIN
    public LoginResponse login(LoginRequest req) {

        Usuario user = usuarioRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(req.getSenha(), user.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        String token = jwtUtil.gerarToken(user.getEmail(), user.getRole().getNome());

        return new LoginResponse(
                user.getNome(),
                user.getEmail(),
                user.getRole().getNome(),
                token
        );
    }

    // USUÁRIO LOGADO
    public Usuario getUsuarioLogado() {

        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getPrincipal() == null || "anonymousUser".equals(auth.getPrincipal())) {
            return null;
        }

        if (!(auth.getPrincipal() instanceof AuthUserDetails userDetails)) {
            throw new RuntimeException("Usuário não autenticado");
        }

        Long id = userDetails.getId();

        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário logado não encontrado"));
    }
}
