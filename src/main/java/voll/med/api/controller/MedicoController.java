package voll.med.api.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voll.med.api.entity.Medico;
import voll.med.api.service.MedicoService;

@RestController
@RequestMapping("/medicos")
@CrossOrigin(origins = "*")
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
    public ResponseEntity<?> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(medicoService.getAllMedicos(pageable));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{slug}")
    public ResponseEntity<?> getMedicoPorSlug(@PathVariable String slug) {
        try {
            Medico medico = medicoService.getMedicoPorSlug(slug);
            return ResponseEntity.ok(medico);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{slug}")
    public ResponseEntity<?> deleteMedico(@PathVariable String slug) {
        try {
            Medico medico = medicoService.deleteMedico(slug);
            return ResponseEntity.ok("Médico excluído com sucesso.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/editar/{slug}")
    public ResponseEntity<?> editarMedico(@PathVariable String slug, @RequestBody Medico medico) {
        try {
            Medico medicoEditar = medicoService.editMedicoBySlug(slug, medico);
            return ResponseEntity.ok(medicoEditar);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
