package br.com.desafio.remessa.domains.cambio;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Cambio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal valorConvertidoUSD;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal cotacaoAtual;

    @Column(precision = 10, scale = 4)
    private BigDecimal variacaoCambialBRL;

    @Column(precision = 5, scale = 2)
    private BigDecimal variacaoCambialPercentual;

    @Column(nullable = false)
    private LocalDateTime dataCotacao;

    public Cambio() {}

    public Cambio(BigDecimal valorConvertidoUSD, BigDecimal cotacaoAtual, BigDecimal variacaoCambialAbsoluta,
                  BigDecimal variacaoCambialPercentual, LocalDateTime dataCotacao) {
        this.valorConvertidoUSD = valorConvertidoUSD;
        this.cotacaoAtual = cotacaoAtual;
        this.variacaoCambialBRL = variacaoCambialAbsoluta;
        this.variacaoCambialPercentual = variacaoCambialPercentual;
        this.dataCotacao = dataCotacao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorConvertidoUSD() {
        return valorConvertidoUSD;
    }

    public void setValorConvertidoUSD(BigDecimal valorConvertidoUSD) {
        this.valorConvertidoUSD = valorConvertidoUSD;
    }

    public BigDecimal getCotacaoAtual() {
        return cotacaoAtual;
    }

    public void setCotacaoAtual(BigDecimal cotacaoCompra) {
        this.cotacaoAtual = cotacaoCompra;
    }

    public BigDecimal getVariacaoCambialBRL() {
        return variacaoCambialBRL;
    }

    public void setVariacaoCambialAbsoluta(BigDecimal variacaoCambialBRL) {
        this.variacaoCambialBRL = variacaoCambialBRL;
    }

    public BigDecimal getVariacaoCambialPercentual() {
        return variacaoCambialPercentual;
    }

    public void setVariacaoCambialPercentual(BigDecimal variacaoCambialPercentual) {
        this.variacaoCambialPercentual = variacaoCambialPercentual;
    }

    public LocalDateTime getDataCotacao() {
        return dataCotacao;
    }

    public void setDataCotacao(LocalDateTime dataCotacao) {
        this.dataCotacao = dataCotacao;
    }
}
