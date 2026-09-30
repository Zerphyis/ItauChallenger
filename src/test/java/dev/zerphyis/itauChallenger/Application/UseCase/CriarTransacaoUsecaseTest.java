package dev.zerphyis.itauChallenger.Application.UseCase;

import dev.zerphyis.itauChallenger.Domain.Entity.Transacao;
import dev.zerphyis.itauChallenger.Domain.Repository.RepositoryTransacao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarTransacaoUseCaseTest {

    @Mock
    private RepositoryTransacao repository;

    @InjectMocks
    private CriarTransacaoUsecase useCase;

    @Test
    void deveCriarESalvarTransacaoComSucesso() {
        BigDecimal valor = new BigDecimal("150.50");
        OffsetDateTime dataHora = OffsetDateTime.now();

        useCase.executar(valor, dataHora);

        verify(repository, times(1)).salvar(any(Transacao.class));
    }

    @Test
    void deveLancarExcecaoQuandoRepositoryFalhar() {
        BigDecimal valor = new BigDecimal("150.50");
        OffsetDateTime dataHora = OffsetDateTime.now();
        doThrow(new RuntimeException("Erro ao salvar no banco")).when(repository).salvar(any(Transacao.class));

        assertThrows(RuntimeException.class, () -> useCase.executar(valor, dataHora));
        verify(repository, times(1)).salvar(any(Transacao.class));
    }
}