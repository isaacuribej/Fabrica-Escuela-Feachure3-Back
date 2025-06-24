package com.prueba.prueba.Agentes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AgentesResolverTest {

    @Mock
    private AgentesRepositorio agentesRepositorio;

    @InjectMocks
    private AgentesResolver agentesResolver;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listaAgentes_ShouldReturnListOfAgents() {
        Agentes agente1 = new Agentes();
        agente1.setId(1);
        agente1.setNombre("Agente 1");

        Agentes agente2 = new Agentes();
        agente2.setId(2);
        agente2.setNombre("Agente 2");

        List<Agentes> agentesList = Arrays.asList(agente1, agente2);

        when(agentesRepositorio.findAll()).thenReturn(agentesList);

        List<Agentes> result = agentesResolver.listaAgentes();

        assertEquals(2, result.size());
        assertEquals("Agente 1", result.get(0).getNombre());
        assertEquals("Agente 2", result.get(1).getNombre());
        verify(agentesRepositorio, times(1)).findAll();
    }

    @Test
    void buscaAgente_WhenAgentFound_ShouldReturnAgent() {
        Agentes agente = new Agentes();
        agente.setId(1);
        agente.setNombre("Agente Test");

        when(agentesRepositorio.findById(1)).thenReturn(Optional.of(agente));

        Agentes result = agentesResolver.buscaAgente(1);

        assertNotNull(result);
        assertEquals("Agente Test", result.getNombre());
        verify(agentesRepositorio, times(1)).findById(1);
    }

    @Test
    void buscaAgente_WhenAgentNotFound_ShouldThrowException() {
        when(agentesRepositorio.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> agentesResolver.buscaAgente(1));

        assertTrue(exception.getMessage().contains("Agente con id 1 no encontrado"));
        verify(agentesRepositorio, times(1)).findById(1);
    }
}