package voll.med.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voll.med.api.entity.Medico;
import voll.med.api.repository.MedicoRepository;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico saveMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    public List<Medico> getAllMedicos() {
        return medicoRepository.findAll();
    }
}
