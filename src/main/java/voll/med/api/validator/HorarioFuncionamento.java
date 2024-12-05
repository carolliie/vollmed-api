package voll.med.api.validator;

import jakarta.persistence.EntityNotFoundException;
import voll.med.api.entity.DadosAgendamentoConsulta;

import java.time.DayOfWeek;

public class HorarioFuncionamento {

    public void validarAgendamento(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisFechamento = dataConsulta.getHour() > 18;

        if (domingo || antesDaAberturaDaClinica || depoisFechamento) {
            throw new EntityNotFoundException("Consulta fora do horário de funcionamento.");
        }
    }

}
