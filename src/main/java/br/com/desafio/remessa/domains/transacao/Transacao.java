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

    public Transacao() {

    }

    public Transacao(Carteira pagador, Carteira recebedor, BigDecimal vlrTransferenciaBRL, Timestamp dhTransferencia) {
        this.pagador = pagador;
        this.recebedor = recebedor;
        this.vlrTransferenciaBRL = vlrTransferenciaBRL;
        this.dhTransferencia = dhTransferencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Carteira getPagador() {
        return pagador;
    }

    public void setPagador(Carteira pagador) {
        this.pagador = pagador;
    }

    public Carteira getRecebedor() {
        return recebedor;
    }

    public void setRecebedor(Carteira recebedor) {
        this.recebedor = recebedor;
    }

    public BigDecimal getVlrTransferenciaBRL() {
        return vlrTransferenciaBRL;
    }

    public void setVlrTransferenciaBRL(BigDecimal vlrTransferenciaBRL) {
        this.vlrTransferenciaBRL = vlrTransferenciaBRL;
    }

    public Timestamp getDhTransferencia() {
        return dhTransferencia;
    }

    public void setDhTransferencia(Timestamp dhTransferencia) {
        this.dhTransferencia = dhTransferencia;
    }

    public Cambio getCambio() {
        return cambio;
    }

    public void setCambio(Cambio cambio) {
        this.cambio = cambio;
    }
}
