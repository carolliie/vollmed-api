package voll.med.api.entity;

public record DadosPacientes(String nome, String cpf, String telefone, String email, DadosEndereco endereco, Especialidade especialidade, String slug) {
}
