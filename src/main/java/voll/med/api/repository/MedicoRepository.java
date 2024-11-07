package voll.med.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import voll.med.api.entity.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
