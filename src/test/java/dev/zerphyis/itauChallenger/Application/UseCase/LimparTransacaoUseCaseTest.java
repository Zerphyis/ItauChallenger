package dev.zerphyis.itauChallenger.Application.UseCase;

import dev.zerphyis.itauChallenger.Domain.Repository.RepositoryTransacao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LimparTransacaoUseCaseTest {

    @Mock
    private RepositoryTransacao repository;

    @InjectMocks
    private LimparTransacaoUseCase useCase;

    @Test
    void deveLimparTransacoesComSucesso() {
        useCase.executar();

        verify(repository, times(1)).limpar();
    }
}