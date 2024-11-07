package voll.med.api.controller;

import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voll.med.api.entity.Medico;
import voll.med.api.service.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<?> cadastrarMedico(@RequestBody Medico medico) {
        try {
            Medico novoMedico = medicoService.saveMedico(medico);
            return ResponseEntity.ok(novoMedico);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarMedicos() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(medicoService.getAllMedicos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
