package dev.zerphyis.itauChallenger.Domain.Repository;

import dev.zerphyis.itauChallenger.Domain.Entity.Transacao;

import java.util.List;

public interface RepositoryTransacao {
    void salvar(Transacao transacao);
    void limpar();
    List<Transacao> buscarUltimos60Segundos();
}
