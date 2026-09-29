package dev.zerphyis.itauChallenger.Application.Dto;

public record EstatisticasResponse(long count,
                                   double sum,
                                   double avg,
                                   double min,
                                   double max) {
}
