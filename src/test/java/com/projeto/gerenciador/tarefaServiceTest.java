package com.projeto.gerenciador;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.projeto.gerenciador.model.tarefa;
import com.projeto.gerenciador.repository.tarefaRepository;
import com.projeto.gerenciador.service.tarefaService;

@ExtendWith(MockitoExtension.class)
public class tarefaServiceTest {

    @Mock
    private tarefaRepository tarefaRepository;

    @InjectMocks
    private tarefaService tarefaService;

    private tarefa tarefaTeste;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        tarefaTeste = new tarefa("Título", "Descrição", "Pendente", "Nenhuma",
                LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    public void testCriarTarefa() {
        when(tarefaRepository.save(tarefaTeste)).thenReturn(tarefaTeste);

        tarefa resultado = tarefaService.criarTarefa(tarefaTeste);

        assertEquals(tarefaTeste, resultado);
        verify(tarefaRepository, times(1)).save(tarefaTeste);
    }

    @Test
    public void testListarTarefas() {
        List<tarefa> tarefas = Arrays.asList(tarefaTeste);
        when(tarefaRepository.findAll()).thenReturn(tarefas);

        List<tarefa> resultado = tarefaService.listarTarefas();

        assertEquals(1, resultado.size());
        verify(tarefaRepository, times(1)).findAll();
    }

    @Test
    public void testBuscarTarefaPorId() {
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefaTeste));

        Optional<tarefa> resultado = tarefaService.buscarTarefaPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(tarefaTeste, resultado.get());
        verify(tarefaRepository, times(1)).findById(1L);
    }

    @Test
    public void testAtualizarTarefa() {
        when(tarefaRepository.save(tarefaTeste)).thenReturn(tarefaTeste);

        tarefa resultado = tarefaService.atualizarTarefa(tarefaTeste);

        assertEquals(tarefaTeste, resultado);
        verify(tarefaRepository, times(1)).save(tarefaTeste);
    }

    @Test
    public void testDeletarTarefa() {
        doNothing().when(tarefaRepository).deleteById(1L);

        tarefaService.deletarTarefa(1L);

        verify(tarefaRepository, times(1)).deleteById(1L);
    }
}
