package voll.med.api.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import voll.med.api.entity.Agendamento;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    Optional<Agendamento> findByDataSlug(String slug);

    boolean existsByMedicoAndData(Long medico, LocalDateTime data);

    boolean existsByPacienteAndDataBetween(@NotNull Long paciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
