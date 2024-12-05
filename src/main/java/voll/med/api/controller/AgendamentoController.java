package voll.med.api.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import voll.med.api.entity.Agendamento;
import voll.med.api.entity.DadosAgendamentoConsulta;
import voll.med.api.service.AgendamentoService;

@RestController
@RequestMapping("agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta dadosAgendamento) {
        try {
            Agendamento agendamento = agendamentoService.salvarAgendamento(dadosAgendamento);
            return ResponseEntity.ok(agendamento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /*@GetMapping
    public ResponseEntity<?> listarAgendamentos(@PageableDefault(size = 10, sort = {"data"}) Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(agendamentoService.getAllAgendamentos(pageable));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /*@GetMapping("/{slug}")
    public ResponseEntity<?> buscarAgendamentoPorSlug(@PathVariable String slug) {
        try {
            Agendamento agendamento = agendamentoService.buscarAgendamentorPorData(slug);
            return ResponseEntity.ok(agendamento);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/editar/{slug}")
    public ResponseEntity<?> editarAgendamento(@PathVariable String slug, @Valid @RequestBody Agendamento dadosAgendamento) {
        try {
            Agendamento agendamentoEditar = agendamentoService.editarAgendamentoPorSlug(dadosAgendamento, slug);
            return ResponseEntity.ok(agendamentoEditar);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{slug}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> deletarAgendamento(@PathVariable String slug) {
        try {
            Agendamento agendamentoDeletar = agendamentoService.deletarAgendamento(slug);
            return ResponseEntity.ok("Médico excluído com sucesso.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }*/

}
