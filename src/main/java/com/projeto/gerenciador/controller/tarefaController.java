package com.projeto.gerenciador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.gerenciador.model.tarefa;
import com.projeto.gerenciador.service.tarefaService;

@RestController
@RequestMapping("/api/tarefas")
public class tarefaController {

    @Autowired
    private tarefaService tarefaService;

    // Endpoint para criar uma nova tarefa
    @PostMapping
    public ResponseEntity<tarefa> criarTarefa(@RequestBody tarefa tarefa) {
        tarefa novaTarefa = tarefaService.criarTarefa(tarefa);
        return ResponseEntity.ok(novaTarefa);
    }

    // Endpoint para listar todas as tarefas
    @GetMapping
    public ResponseEntity<List<tarefa>> listarTarefas() {
        List<tarefa> tarefas = tarefaService.listarTarefas();
        return ResponseEntity.ok(tarefas);
    }

    // Endpoint para buscar uma tarefa por ID
    @GetMapping("/{id}")
    public ResponseEntity<tarefa> buscarTarefaPorId(@PathVariable Long id) {
        return tarefaService.buscarTarefaPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para atualizar uma tarefa
    @PutMapping("/{id}")
    public ResponseEntity<tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody tarefa tarefaDetalhada) {
        return tarefaService.buscarTarefaPorId(id)
            .map(tarefaExistente -> {
                tarefaExistente.setNome(tarefaDetalhada.getNome());
                tarefaExistente.setDescricao(tarefaDetalhada.getDescricao());
                tarefaExistente.setStatus(tarefaDetalhada.getStatus());
                tarefaExistente.setObservacoes(tarefaDetalhada.getObservacoes());
                tarefa tarefaAtualizada = tarefaService.atualizarTarefa(tarefaExistente);
                return ResponseEntity.ok(tarefaAtualizada);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para deletar uma tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        return tarefaService.buscarTarefaPorId(id)
            .map(tarefa -> {
                tarefaService.deletarTarefa(id);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}
