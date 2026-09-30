package dev.zerphyis.itauChallenger.Infra.Persistance.RepositoryJpa.Adapter;

import dev.zerphyis.itauChallenger.Domain.Entity.Transacao;
import dev.zerphyis.itauChallenger.Domain.Repository.RepositoryTransacao;
import dev.zerphyis.itauChallenger.Infra.Persistance.EntityJpa.TransacaoJpa;
import dev.zerphyis.itauChallenger.Infra.Persistance.RepositoryJpa.RepositoryTransacaoJpa;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RepositoryAdapter implements RepositoryTransacao {

    private final RepositoryTransacaoJpa jpaRepository;

    public RepositoryAdapter(RepositoryTransacaoJpa jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void salvar(Transacao transacao) {
        TransacaoJpa transacaoJpa = new TransacaoJpa();
        transacaoJpa.setValor(transacao.getValor());
        transacaoJpa.setDataHora(transacao.getDatahora());

        jpaRepository.save(transacaoJpa);
    }

    @Override
    public void limpar() {
        jpaRepository.deleteAll();
    }

    @Override
    public List<Transacao> buscarUltimos60Segundos() {
        OffsetDateTime limite = OffsetDateTime.now().minusSeconds(60);

        List<TransacaoJpa> jpaList = jpaRepository.findByDataHoraAfter(limite);

        return jpaList.stream()
                .map(jpa -> new Transacao(jpa.getValor(), jpa.getDataHora()))
                .collect(Collectors.toList());
    }
}