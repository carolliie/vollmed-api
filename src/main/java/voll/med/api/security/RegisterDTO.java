package voll.med.api.security;

import voll.med.api.entity.UsuarioRole;

public record RegisterDTO(String username, String email, String password, UsuarioRole role) {
}
