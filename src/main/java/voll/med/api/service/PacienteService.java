package voll.med.api.service;

import com.github.slugify.Slugify;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import voll.med.api.entity.Paciente;
import voll.med.api.repository.PacienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    final Slugify slugify = Slugify.builder().build();

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente savePaciente(Paciente paciente) {
        String nomePacienteSlug = slugify.slugify(paciente.getNome());
        paciente.setSlug(nomePacienteSlug);
        return pacienteRepository.save(paciente);
    }

    public Page<Paciente> getAllPacientes(Pageable pageable) {
        return pacienteRepository.findAll(pageable);
    }

    public Paciente getPacienteBySlug(String slug) {
        Optional<Paciente> paciente = pacienteRepository.findBySlug(slug);

        if(paciente.isPresent()) {
            return paciente.get();
        } else {
            throw new EntityNotFoundException("Paciente não encontrado ou não existe.");
        }
    }

    public Paciente editPacienteBySlug(String slug, Paciente paciente) {
        Optional<Paciente> pacienteEditado = pacienteRepository.findBySlug(slug);

        if (pacienteEditado.isPresent()) {
            Paciente dadosPaciente = pacienteEditado.get();

            if (paciente.getNome() != null) {
                dadosPaciente.setNome(paciente.getNome());
                String novoPacienteSlug = slugify.slugify(paciente.getNome());
                dadosPaciente.setSlug(novoPacienteSlug);
            }
            if (paciente.getEmail() != null) {
                dadosPaciente.setEmail(paciente.getEmail());
            }
            if (paciente.getTelefone() != null) {
                dadosPaciente.setTelefone(paciente.getTelefone());
            }
            if (paciente.getEndereco() != null) {
                dadosPaciente.setEndereco(paciente.getEndereco());
            }
            if(paciente.getCpf() != null) {
                dadosPaciente.setCpf(paciente.getCpf());
            }

            pacienteRepository.save(dadosPaciente);
            return dadosPaciente;
        } else {
            throw new EntityNotFoundException("Paciente não encontrado ou não existe.");
        }
    }

    public Paciente deletePaciente(String slug) {
        Optional<Paciente> pacienteDeletado = pacienteRepository.findBySlug(slug);

        if (pacienteDeletado.isPresent()) {
            pacienteRepository.delete(pacienteDeletado.get());
            return pacienteDeletado.get();
        } else {
            throw new EntityNotFoundException("Paciente não encontrado ou não existe.");
        }
    }
}
