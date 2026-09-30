package dev.zerphyis.itauChallenger.Infra.Persistance.RepositoryJpa;

import dev.zerphyis.itauChallenger.Domain.Entity.Transacao;
import dev.zerphyis.itauChallenger.Infra.Persistance.EntityJpa.TransacaoJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface RepositoryTransacaoJpa extends JpaRepository<TransacaoJpa, UUID> {
    @Query("SELECT t FROM TransacaoJpa t WHERE t.dataHora >= :limite")
    List<TransacaoJpa> findByDataHoraAfter(@Param("limite") OffsetDateTime limite);

}
