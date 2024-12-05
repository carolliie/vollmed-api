package voll.med.api.repository;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import voll.med.api.entity.Especialidade;
import voll.med.api.entity.Medico;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Optional<Medico> findByNome(String nome);

    Optional<Medico> findBySlug(String slug);

    @Query("""
            SELECT m FROM Medico m
            WHERE m.ativo = true
            AND
            m.especialidade = :especialidade
            AND
            m.id NOT IN(
                SELECT c.medico.id FROM Agendamento c
                WHERE c.data = :data
            )
            ORDER BY rand()
            LIMIT 1
            """)
    Medico escolherMedicoEspecializadoDisponivel(Especialidade especialidade, @NotNull @Future LocalDateTime data);

    @Query("""
            SELECT m.ativo
            FROM Medico m
            WHERE m.id = :id
            """)
    Boolean findAtivoById(@NotNull Long idMedico);
}
