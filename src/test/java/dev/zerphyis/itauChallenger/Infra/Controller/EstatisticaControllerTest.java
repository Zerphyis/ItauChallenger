package dev.zerphyis.itauChallenger.Infra.Controller;

import dev.zerphyis.itauChallenger.Application.Dto.EstatisticasResponse;
import dev.zerphyis.itauChallenger.Application.UseCase.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EstatisticaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TransacaoService service;

    @InjectMocks
    private EstatisticaController controller;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void deveRetornarEstatisticasEStatus200() throws Exception {
        EstatisticasResponse responseMock = new EstatisticasResponse(5L, 1000.0, 200.0, 50.0, 500.0);

        when(service.calcularEstatisticas()).thenReturn(responseMock);

        mockMvc.perform(get("/estatistica"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(5))
                .andExpect(jsonPath("$.sum").value(1000.0))
                .andExpect(jsonPath("$.avg").value(200.0))
                .andExpect(jsonPath("$.min").value(50.0))
                .andExpect(jsonPath("$.max").value(500.0));
    }
}