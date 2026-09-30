package dev.zerphyis.itauChallenger.Application.UseCase;

import dev.zerphyis.itauChallenger.Application.Dto.EstatisticasResponse;
import dev.zerphyis.itauChallenger.Application.InterfaceCase.CalcularEstatisticasInterfaceCase;
import dev.zerphyis.itauChallenger.Application.InterfaceCase.CriarTransacaoInterfaceCase;
import dev.zerphyis.itauChallenger.Application.InterfaceCase.LimparTransacaoInterfaceCase;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class TransacaoService {

    private final CriarTransacaoInterfaceCase criarTransacaoUsecase;
    private final LimparTransacaoInterfaceCase limparTransacaoUseCase;
    private final CalcularEstatisticasInterfaceCase calcularEstatisticasUseCase;

    public TransacaoService(
            CriarTransacaoInterfaceCase criarTransacaoUsecase,
            LimparTransacaoInterfaceCase limparTransacaoUseCase,
            CalcularEstatisticasInterfaceCase calcularEstatisticasUseCase) {

        this.criarTransacaoUsecase = criarTransacaoUsecase;
        this.limparTransacaoUseCase = limparTransacaoUseCase;
        this.calcularEstatisticasUseCase = calcularEstatisticasUseCase;
    }

    public EstatisticasResponse calcularEstatisticas() {
        return calcularEstatisticasUseCase.executar();
    }

    public void criarTransacao(BigDecimal valor, OffsetDateTime dataHora) {
        criarTransacaoUsecase.executar(valor, dataHora);
    }

    public void limparTransacoes() {
        limparTransacaoUseCase.executar();
    }
}