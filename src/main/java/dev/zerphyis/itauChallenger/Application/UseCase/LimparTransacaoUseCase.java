package dev.zerphyis.itauChallenger.Application.UseCase;

import dev.zerphyis.itauChallenger.Application.InterfaceCase.LimparTransacaoInterfaceCase;
import dev.zerphyis.itauChallenger.Domain.Repository.RepositoryTransacao;

public class LimparTransacaoUseCase implements LimparTransacaoInterfaceCase {
    private final RepositoryTransacao repository;

    public LimparTransacaoUseCase(RepositoryTransacao repository) {
        this.repository = repository;
    }

    @Override
    public void executar() {
        repository.limpar();
    }
}
