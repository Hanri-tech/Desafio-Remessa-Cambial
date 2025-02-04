package br.com.desafio.remessa.domains.transacao;

import br.com.desafio.remessa.domains.cambio.Cambio;
import br.com.desafio.remessa.domains.carteira.Carteira;
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
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "pagadorId")
    private Carteira pagador;

    @ManyToOne
    @JoinColumn(name = "recebedorId")
    private Carteira recebedor;

    @Column(precision = 10, scale = 2)
    private BigDecimal vlrTransferenciaBRL;

    private Timestamp dhTransferencia;

    @ManyToOne
    @JoinColumn(name = "cambioId")
    private Cambio cambio;

}
