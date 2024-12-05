package voll.med.api.validator;

import jakarta.persistence.EntityNotFoundException;
import voll.med.api.entity.DadosAgendamentoConsulta;
import voll.med.api.repository.MedicoRepository;

public class MedicoAtivo {

    private MedicoRepository medicoRepository;

    public void validarMedicoAtivo(DadosAgendamentoConsulta dados) {

        if (dados.idMedico() == null) {
            return;
        }

        var medicoEstaAtivo = medicoRepository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo) {
            throw new EntityNotFoundException("Consulta não pode ser agendada com médico inativo.");
        }

    }

}
