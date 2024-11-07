package voll.med.api.entity;

public record DadosMedicos(String nome, String crm, String telefone, String email, DadosEndereco endereco, Especialidade especialidade) {
}
