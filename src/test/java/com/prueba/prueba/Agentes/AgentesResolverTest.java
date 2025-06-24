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
    void buscaAgente_WhenAgentFound_ShouldReturnAgent() {
        Agentes agente = new Agentes();
        agente.setId_agente(1);
        agente.setNombreUsuario("Agente Test");

        when(agentesRepositorio.findById(1)).thenReturn(Optional.of(agente));

        Agentes result = agentesResolver.buscaAgente(1);

        assertNotNull(result);
        assertEquals("Agente Test", result.getNombreUsuario());
        verify(agentesRepositorio, times(1)).findById(1);
    }