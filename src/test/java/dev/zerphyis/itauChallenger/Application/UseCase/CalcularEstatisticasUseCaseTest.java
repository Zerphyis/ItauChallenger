package dev.zerphyis.itauChallenger.Application.UseCase;

import dev.zerphyis.itauChallenger.Application.Dto.EstatisticasResponse;
import dev.zerphyis.itauChallenger.Domain.Entity.Transacao;
import dev.zerphyis.itauChallenger.Domain.Repository.RepositoryTransacao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CalcularEstatisticasUseCaseTest {
    @Mock
    private RepositoryTransacao repository;

    @InjectMocks
    private CalcularEstatisticasUseCase useCase;

    @Test
    void deveCalcularEstatisticasComSucessoQuandoHouverTransacoes() {
        Transacao t1 = new Transacao(new BigDecimal("100.0"), OffsetDateTime.now());
        Transacao t2 = new Transacao(new BigDecimal("200.0"), OffsetDateTime.now());
        when(repository.buscarUltimos60Segundos()).thenReturn(Arrays.asList(t1, t2));

        EstatisticasResponse response = useCase.executar();

        assertEquals(2, response.count());
        assertEquals(300.0, response.sum());
        assertEquals(150.0, response.avg());
        assertEquals(100.0, response.min());
        assertEquals(200.0, response.max());
        verify(repository, times(1)).buscarUltimos60Segundos();
    }

    @Test
    void deveRetornarEstatisticasZeradasQuandoNaoHouverTransacoes() {
        when(repository.buscarUltimos60Segundos()).thenReturn(Collections.emptyList());

        EstatisticasResponse response = useCase.executar();

        assertEquals(0, response.count());
        assertEquals(0.0, response.sum());
        assertEquals(0.0, response.avg());
        assertEquals(0.0, response.min());
        assertEquals(0.0, response.max());
        verify(repository, times(1)).buscarUltimos60Segundos();
    }

}