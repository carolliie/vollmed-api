package voll.med.api.validator;

import jakarta.persistence.EntityNotFoundException;
import voll.med.api.entity.Agendamento;
import voll.med.api.entity.DadosAgendamentoConsulta;
import voll.med.api.repository.AgendamentoRepository;

public class MedicoComConsultaMarcada {

    private AgendamentoRepository agendamentoRepository;

    public void validarAgendamento(DadosAgendamentoConsulta dados) {
        var medicoPossuiConsultaMarcada = agendamentoRepository.existsByMedicoAndData(dados.idMedico(), dados.data());

        if (medicoPossuiConsultaMarcada) {
            throw new EntityNotFoundException("Médico possui consulta no horário selecionado");
        }
    }

}
