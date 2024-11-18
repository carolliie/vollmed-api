package voll.med.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import voll.med.api.entity.Paciente;
import voll.med.api.service.PacienteService;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<?> createPaciente(@RequestBody Paciente paciente) {
        try {
            Paciente novoPaciente = pacienteService.savePaciente(paciente);
            return ResponseEntity.ok(novoPaciente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(pacienteService.getAllPacientes(pageable));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{slug}")
    public ResponseEntity<?> listarPacientePorSlug(@PathVariable String slug) {
        try {
            Paciente paciente = pacienteService.getPacienteBySlug(slug);
            return ResponseEntity.ok(paciente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/editar/{slug}")
    public ResponseEntity<?> editarPaciente(@PathVariable String slug, @RequestBody Paciente paciente) {
        try {
            Paciente pacienteEditado = pacienteService.editPacienteBySlug(slug, paciente);
            return ResponseEntity.ok(pacienteEditado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{slug}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> deletarPaciente(@PathVariable String slug) {
        try {
            Paciente pacienteDeletado = pacienteService.deletePaciente(slug);
            return ResponseEntity.ok(pacienteDeletado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
