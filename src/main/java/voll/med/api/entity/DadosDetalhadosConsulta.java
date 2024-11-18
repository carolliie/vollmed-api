package voll.med.api.entity;

import java.time.LocalDateTime;

public record DadosDetalhadosConsulta(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data,
        String dataSlug
) {}
