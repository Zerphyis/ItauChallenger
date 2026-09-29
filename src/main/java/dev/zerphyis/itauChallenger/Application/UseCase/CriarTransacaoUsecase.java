package dev.zerphyis.itauChallenger.Application.UseCase;

import dev.zerphyis.itauChallenger.Application.InterfaceCase.CriarTransacaoInterfaceCase;
import dev.zerphyis.itauChallenger.Domain.Entity.Transacao;
import dev.zerphyis.itauChallenger.Domain.Repository.RepositoryTransacao;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class CriarTransacaoUsecase implements CriarTransacaoInterfaceCase {
    private final RepositoryTransacao repository;

    public CriarTransacaoUsecase(RepositoryTransacao repository) {
        this.repository = repository;
    }

    @Override
    public void executar(BigDecimal valor, OffsetDateTime dataHora) {
        Transacao novaTransacao= new Transacao(valor,dataHora);
        repository.salvar(novaTransacao);

    }
}
