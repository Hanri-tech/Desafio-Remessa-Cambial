package br.com.desafio.remessa.domains.cotacao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.security.Timestamp;

@Entity
@Getter
@Setter
public class CotacaoMoeda {

    @Id
    private int id;
    private BigDecimal valor;
    private Timestamp data;
}
