package dev.zerphyis.itauChallenger.Application.Dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransacaoRequest(BigDecimal valor, OffsetDateTime datahora) {
}
