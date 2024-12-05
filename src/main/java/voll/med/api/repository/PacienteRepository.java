package voll.med.api.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import voll.med.api.entity.Paciente;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findBySlug(String slug);

    @Query("""
            SELECT p.ativo
            FROM Paciente m
            WHERE p.id = :id
            """)
    Boolean findAtivoById(@NotNull Long idPaciente);
}
