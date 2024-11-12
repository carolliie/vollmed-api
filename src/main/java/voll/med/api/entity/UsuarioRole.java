package voll.med.api.entity;

public enum UsuarioRole {
    MEDICO("medico"),
    PACIENTE("paciente"),
    ADMIN("admin");

    private String role;

    private UsuarioRole(String role) { this.role = role; }

    public String getRole() { return role; }
}
