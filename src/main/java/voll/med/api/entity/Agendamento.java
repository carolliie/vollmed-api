/*package voll.med.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "agendamento")
@Table(name = "agendamentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Paciente paciente;

    @Embedded
    private Medico medico;

    private Date data;

}*/
