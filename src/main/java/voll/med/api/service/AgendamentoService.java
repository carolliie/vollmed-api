package voll.med.api.service;

import com.github.slugify.Slugify;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import voll.med.api.entity.Agendamento;
import voll.med.api.entity.DadosAgendamentoConsulta;
import voll.med.api.repository.AgendamentoRepository;
import voll.med.api.repository.MedicoRepository;
import voll.med.api.repository.PacienteRepository;

import java.util.Optional;

@Service
public class AgendamentoService {

    final Slugify slugify = Slugify.builder().build();

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public Agendamento salvarAgendamento(DadosAgendamentoConsulta dados) {

        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new EntityNotFoundException("Paciente informado não existe!");
        }

        if (!medicoRepository.existsById(dados.idMedico())) {
            throw new EntityNotFoundException("Médico informado não existe!");
        }

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = medicoRepository.getReferenceById(dados.idMedico());

        var agendamento = new Agendamento(null, paciente, medico, dados.data());
        return agendamentoRepository.save(agendamento);
    }

    public Page<Agendamento> getAllAgendamentos(Pageable pageable) {
        return agendamentoRepository.findAll(pageable);
    }

    public Agendamento buscarAgendamentorPorData(String slug) {
        Optional<Agendamento> agendamento = agendamentoRepository.findByDataSlug(slug);
        if (agendamento.isPresent()) {
            return agendamento.get();
        } else {
            throw new EntityNotFoundException("Agendamento não encontrado ou não existe.");
        }
    }

    public Agendamento editarAgendamentoPorSlug(Agendamento agendamento, String slug) {
        Optional<Agendamento> optionalAgendamento = agendamentoRepository.findByDataSlug(slug);

        if (optionalAgendamento.isPresent()) {

            Agendamento dadosAgendamento = optionalAgendamento.get();

            if (agendamento.getPaciente() != null) {
                dadosAgendamento.setPaciente(agendamento.getPaciente());
            }

            if (agendamento.getMedico() != null) {
                dadosAgendamento.setMedico(agendamento.getMedico());
            }

            if (agendamento.getData() != null) {
                dadosAgendamento.setData(agendamento.getData());
                String newSlug = slugify.slugify(String.valueOf(agendamento.getData()));
                dadosAgendamento.setDataSlug(newSlug);
            }

            agendamentoRepository.save(dadosAgendamento);
            return agendamento;
        } else {
            throw new EntityNotFoundException("Agendamento não encontrado.");
        }
    }

    public Agendamento deletarAgendamento(String slug) {
        Optional<Agendamento> optionalAgendamento = agendamentoRepository.findByDataSlug(slug);

        if (optionalAgendamento.isPresent()) {
            agendamentoRepository.delete(optionalAgendamento.get());
            return optionalAgendamento.get();
        } else {
            throw new EntityNotFoundException("Agendamento não encontrado.");
        }
    }
}
