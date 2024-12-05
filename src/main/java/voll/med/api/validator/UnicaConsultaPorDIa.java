package voll.med.api.validator;

import jakarta.persistence.EntityNotFoundException;
import voll.med.api.entity.DadosAgendamentoConsulta;
import voll.med.api.repository.AgendamentoRepository;

public class UnicaConsultaPorDIa {

    private AgendamentoRepository agendamentoRepository;

    public void validarConsulta(DadosAgendamentoConsulta dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);

        var pacientePossuiConsulta = agendamentoRepository.existsByPacienteAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);

        if (pacientePossuiConsulta) {
            throw new EntityNotFoundException("Paciente possui consulta no dia.");
        }
    }

}
