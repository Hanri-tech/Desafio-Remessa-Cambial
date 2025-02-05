package br.com.desafio.remessa.domains.cambio;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Cambio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 12, scale = 4)
    private BigDecimal vlrCambioBRLParaUSD;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal cotacaoMoedaHoje;

    @Column(precision = 10, scale = 4)
    private BigDecimal variacaoCambialAbsoluta;

    @Column(precision = 5, scale = 2)
    private BigDecimal variacaoCambialPercentual;

    @Column(nullable = false)
    private LocalDate dataCotacao;

    public Cambio() {}

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getVlrCambioBRLParaUSD() {
        return vlrCambioBRLParaUSD;
    }

    public void setVlrCambioBRLParaUSD(BigDecimal vlrcambioBRLparaUSD) {
        this.vlrCambioBRLParaUSD = vlrcambioBRLparaUSD;
    }

    public BigDecimal getCotacaoMoedaHoje() {
        return cotacaoMoedaHoje;
    }

    public void setCotacaoMoedaHoje(BigDecimal cotacaoCompra) {
        this.cotacaoMoedaHoje = cotacaoCompra;
    }

    public BigDecimal getVariacaoCambialAbsoluta() {
        return variacaoCambialAbsoluta;
    }

    public void setVariacaoCambial(BigDecimal variacaoCambialBRL) {
        this.variacaoCambialAbsoluta = variacaoCambialBRL;
    }

    public BigDecimal getVariacaoCambialPercentual() {
        return variacaoCambialPercentual;
    }

    public void setVariacaoCambialPercentual(BigDecimal variacaoCambialPercentual) {
        this.variacaoCambialPercentual = variacaoCambialPercentual;
    }

    public LocalDate getDataCotacao() {
        return dataCotacao;
    }

    public void setDataCotacao(LocalDate dataCotacao) {
        this.dataCotacao = dataCotacao;
    }
}
