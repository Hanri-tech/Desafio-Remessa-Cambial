package br.com.desafio.remessa.domain.carteira;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false, unique = true)
    private String email;

    private String senha;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento")
    private TipoCarteira tipoCarteira;

    @Column(nullable = false, unique = true)
    private String documento;

    private String carteira;

    @Column(nullable = false)
    private BigDecimal saldoBRL = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal saldoUSD = BigDecimal.ZERO;

}
