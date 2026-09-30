package dev.zerphyis.itauChallenger.Domain.Entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Transacao {
    private  BigDecimal Valor;
    private  OffsetDateTime datahora;


    public Transacao(BigDecimal valor, OffsetDateTime datahora) {
        validar(valor,datahora);
        Valor = valor;
        this.datahora = datahora;
    }

    private void validar(BigDecimal valor, OffsetDateTime datahora){
        if(valor == null || valor.compareTo(BigDecimal.ZERO )< 0){
            throw new IllegalArgumentException("Valor não pode ser negativo");
        }
        if(datahora == null || datahora.isAfter(OffsetDateTime.now())){
            throw new IllegalArgumentException("Data não pode estar no futuro");
        }
    }

    public OffsetDateTime getDatahora() {
        return datahora;
    }

    public BigDecimal getValor() {
        return Valor;
    }

    public void setValor(BigDecimal valor) {
        Valor = valor;
    }

    public void setDatahora(OffsetDateTime datahora) {
        this.datahora = datahora;
    }
}
