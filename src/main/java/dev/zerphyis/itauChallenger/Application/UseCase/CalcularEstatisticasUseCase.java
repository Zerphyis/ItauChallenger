package dev.zerphyis.itauChallenger.Application.UseCase;

import dev.zerphyis.itauChallenger.Application.Dto.EstatisticasResponse;
import dev.zerphyis.itauChallenger.Application.InterfaceCase.CalcularEstatisticasInterfaceCase;
import dev.zerphyis.itauChallenger.Domain.Entity.Transacao;
import dev.zerphyis.itauChallenger.Domain.Repository.RepositoryTransacao;

import java.util.DoubleSummaryStatistics;
import java.util.List;

public class CalcularEstatisticasUseCase implements CalcularEstatisticasInterfaceCase {
    private final RepositoryTransacao repository;

    public CalcularEstatisticasUseCase(RepositoryTransacao repository) {
        this.repository = repository;
    }


    public EstatisticasResponse executar() {
        List<Transacao> transacoes = repository.buscarUltimos60Segundos();

        if (transacoes.isEmpty()) {
            return new EstatisticasResponse(0, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics stats = transacoes.stream()
                .mapToDouble(Transacao::getValor)
                .summaryStatistics();

        return new EstatisticasResponse(
                stats.getCount(),
                stats.getSum(),
                stats.getAverage(),
                stats.getMin(),
                stats.getMax()
        );
    }
}
