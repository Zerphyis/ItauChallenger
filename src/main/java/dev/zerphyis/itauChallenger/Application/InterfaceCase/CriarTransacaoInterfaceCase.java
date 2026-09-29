package dev.zerphyis.itauChallenger.Application.InterfaceCase;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface CriarTransacaoInterfaceCase {
    public void executar(BigDecimal valor, OffsetDateTime dataHora);
}
