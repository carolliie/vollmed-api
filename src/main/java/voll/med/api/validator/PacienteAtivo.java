package voll.med.api.validator;

import jakarta.persistence.EntityNotFoundException;
import voll.med.api.entity.DadosAgendamentoConsulta;
import voll.med.api.repository.PacienteRepository;

public class PacienteAtivo {

    private PacienteRepository pacienteRepository;

    public void validarPaciente(DadosAgendamentoConsulta dados) {
        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
        if (!pacienteEstaAtivo) {
            throw new EntityNotFoundException("Consulta não pode ser agendada com médico inativo.");
        }
    }

}
