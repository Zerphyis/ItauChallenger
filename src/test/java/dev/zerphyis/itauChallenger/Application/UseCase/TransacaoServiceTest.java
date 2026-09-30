package dev.zerphyis.itauChallenger.Application.UseCase;

import dev.zerphyis.itauChallenger.Application.Dto.EstatisticasResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @Mock
    private CriarTransacaoUsecase criarTransacaoUsecase;
    @Mock
    private LimparTransacaoUseCase limparTransacaoUseCase;
    @Mock
    private CalcularEstatisticasUseCase calcularEstatisticasUseCase;

    @InjectMocks
    private TransacaoService service;

    @Test
    void deveChamarUsecaseDeCriarTransacao() {
        BigDecimal valor = new BigDecimal("50.0");
        OffsetDateTime dataHora = OffsetDateTime.now();

        service.criarTransacao(valor, dataHora);

        verify(criarTransacaoUsecase, times(1)).executar(valor, dataHora);
    }

    @Test
    void deveChamarUsecaseDeLimparTransacoes() {
        service.limparTransacoes();
        verify(limparTransacaoUseCase, times(1)).executar();
    }

    @Test
    void deveChamarUsecaseDeCalcularEstatisticas() {
        when(calcularEstatisticasUseCase.executar()).thenReturn(new EstatisticasResponse(1, 10.0, 10.0, 10.0, 10.0));

        EstatisticasResponse response = service.calcularEstatisticas();

        assertNotNull(response);
        verify(calcularEstatisticasUseCase, times(1)).executar();
    }
}