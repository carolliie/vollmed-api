package voll.med.api.service;

import com.github.slugify.Slugify;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voll.med.api.entity.Medico;
import voll.med.api.repository.MedicoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    final Slugify slugify = Slugify.builder().build();

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico saveMedico(Medico medico) {
        String nomeMedicoSlug = slugify.slugify(medico.getNome());
        medico.setSlug(nomeMedicoSlug);
        return medicoRepository.save(medico);
    }

    public List<Medico> getAllMedicos() {
        return medicoRepository.findAll();
    }

    public Medico editMedicoBySlug(String slug, Medico medico) {
        Optional<Medico> medicoOptional = medicoRepository.findBySlug(slug);

        if (medicoOptional.isPresent()) {
            Medico dadosMedicos = medicoOptional.get();

            if (medico.getNome() != null) {
                dadosMedicos.setNome(medico.getNome());
                String newSlug = slugify.slugify(medico.getNome());
                dadosMedicos.setSlug(newSlug);
            }

            if (medico.getCrm() != null) {
                dadosMedicos.setCrm(medico.getCrm());
            }

            if (medico.getEndereco() != null) {
                dadosMedicos.setEndereco(medico.getEndereco());
            }

            if (medico.getTelefone() != null) {
                dadosMedicos.setTelefone(medico.getTelefone());
            }

            if (medico.getEmail() != null) {
                dadosMedicos.setEmail(medico.getEmail());
            }

            if (medico.getEspecialidade() != null) {
                dadosMedicos.setEspecialidade(medico.getEspecialidade());
            }

            medicoRepository.save(dadosMedicos);
            return dadosMedicos;
        } else {
            throw new EntityNotFoundException("Médico não encontrado.");
        }
    }

    public Medico getMedicoPorSlug(String slug) {
        Optional<Medico> medico = medicoRepository.findBySlug(slug);
        if (medico.isPresent()) {
            return medico.get();
        } else {
            throw new EntityNotFoundException("Médico não encontrado ou não existe.");
        }
    }

    public Medico deleteMedico(String slug) {
        Optional<Medico> medico = medicoRepository.findBySlug(slug);
        if (medico.isPresent()) {
            medicoRepository.delete(medico.get());
            return medico.get();
        } else {
            throw new EntityNotFoundException("Médico não encontrado ou não existe.");
        }
    }
}
