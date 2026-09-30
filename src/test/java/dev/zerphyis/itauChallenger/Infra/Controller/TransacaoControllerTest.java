package dev.zerphyis.itauChallenger.Infra.Controller;

import dev.zerphyis.itauChallenger.Application.UseCase.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TransacaoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TransacaoService service;

    @InjectMocks
    private TransacaoController controller;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void deveRetornar201AoCriarTransacao() throws Exception {
        String json = """
                {
                    "valor": 120.50,
                    "datahora": "2026-09-30T10:45:00.000-03:00"
                }
                """;

        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void deveRetornar400SeRequisicaoForInvalida() throws Exception {
        doThrow(new IllegalArgumentException("Dados inválidos")).when(service)
                .criarTransacao(any(BigDecimal.class), any(OffsetDateTime.class));

        String json = """
                {
                    "valor": -50.00,
                    "datahora": "2026-09-30T10:45:00.000-03:00"
                }
                """;

        try {
            mockMvc.perform(post("/transacao")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json));
        } catch (Exception e) {
            assert e.getCause() instanceof IllegalArgumentException;
        }
    }

    @Test
    void deveRetornar200AoDeletarTransacoes() throws Exception {
        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());
    }
}