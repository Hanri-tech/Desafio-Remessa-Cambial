package br.com.desafio.remessa.domain.transacao;

import br.com.desafio.remessa.domain.carteira.Carteira;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne()
    @JoinColumn(name = "pagadorId")
    private Carteira pagador;

    @ManyToOne
    @JoinColumn(name = "recebedorId")
    private Carteira recebedor;

    private BigDecimal vlrTransferencia;

    private Timestamp dhTransferencia;

}
