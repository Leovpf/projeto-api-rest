package com.projeto.gerenciador.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.gerenciador.model.tarefa;
import com.projeto.gerenciador.repository.tarefaRepository;

@Service
public class tarefaService {

    @Autowired
    private tarefaRepository tarefaRepository;

    // Criar uma nova tarefa
    public tarefa criarTarefa(tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    // Listar todas as tarefas
    public List<tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    // Buscar tarefa por ID
    public Optional<tarefa> buscarTarefaPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    // Atualizar a tarefa
    public tarefa atualizarTarefa(tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    // Deletar a tarefa por ID
    public void deletarTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }
}
