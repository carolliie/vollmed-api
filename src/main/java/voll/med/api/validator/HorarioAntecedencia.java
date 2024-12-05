package voll.med.api.validator;

import jakarta.persistence.EntityNotFoundException;
import voll.med.api.entity.DadosAgendamentoConsulta;

import java.time.Duration;
import java.time.LocalDateTime;

public class HorarioAntecedencia {

    public void validarAntecedencia(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var dataAtual = LocalDateTime.now();
        var diferencaEmMin = Duration.between(dataAtual, dataConsulta).toMinutes();

        if (diferencaEmMin < 30) {
            throw new EntityNotFoundException("Consulta deve ser agendada com antecedência mínima de 30 minutos.");
        }
    }

}
