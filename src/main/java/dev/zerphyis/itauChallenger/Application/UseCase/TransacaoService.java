package dev.zerphyis.itauChallenger.Application.UseCase;

import dev.zerphyis.itauChallenger.Application.Dto.EstatisticasResponse;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class TransacaoService {
    private final CriarTransacaoUsecase criarTransacaoUsecase;
    private final LimparTransacaoUseCase limparTransacaoUseCase;
    private final  CalcularEstatisticasUseCase calcularEstatisticasUseCase;

    public TransacaoService(CriarTransacaoUsecase criarTransacaoUsecase, LimparTransacaoUseCase limparTransacaoUseCase, CalcularEstatisticasUseCase calcularEstatisticasUseCase) {
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
