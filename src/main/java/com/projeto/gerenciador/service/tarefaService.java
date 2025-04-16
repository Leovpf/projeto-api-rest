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

    public tarefa criarTarefa(tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public List<tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    public Optional<tarefa> buscarTarefaPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    public tarefa atualizarTarefa(tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public void deletarTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }
}
