package dev.zerphyis.itauChallenger.Infra.Config;

import dev.zerphyis.itauChallenger.Application.InterfaceCase.CalcularEstatisticasInterfaceCase;
import dev.zerphyis.itauChallenger.Application.InterfaceCase.CriarTransacaoInterfaceCase;
import dev.zerphyis.itauChallenger.Application.InterfaceCase.LimparTransacaoInterfaceCase;
import dev.zerphyis.itauChallenger.Application.UseCase.CalcularEstatisticasUseCase;
import dev.zerphyis.itauChallenger.Application.UseCase.CriarTransacaoUsecase;
import dev.zerphyis.itauChallenger.Application.UseCase.LimparTransacaoUseCase;
import dev.zerphyis.itauChallenger.Application.UseCase.TransacaoService;
import dev.zerphyis.itauChallenger.Domain.Repository.RepositoryTransacao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CalcularEstatisticasInterfaceCase calcularEstatisticasUseCase(RepositoryTransacao repository) {
        return new CalcularEstatisticasUseCase(repository);
    }

    @Bean
    public CriarTransacaoInterfaceCase criarTransacaoUseCase(RepositoryTransacao repository) {
        return new CriarTransacaoUsecase(repository);
    }

    @Bean
    public LimparTransacaoInterfaceCase limparTransacaoUseCase(RepositoryTransacao repository) {
        return new LimparTransacaoUseCase(repository);
    }

    @Bean
    public TransacaoService transacaoServiceFacade(
            CalcularEstatisticasUseCase calcularEstatisticasUseCase,
            CriarTransacaoUsecase criarTransacaoUseCase,
            LimparTransacaoUseCase limparTransacaoUseCase
    ) {
        return new TransacaoService(
              criarTransacaoUseCase,
                limparTransacaoUseCase,
                calcularEstatisticasUseCase
        );
    }
}
