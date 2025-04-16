package com.projeto.gerenciador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.gerenciador.model.tarefa;

@Repository
public interface tarefaRepository extends JpaRepository<tarefa, Long> {
    // Aqui você pode definir métodos de consulta personalizados, se necessário.
}
