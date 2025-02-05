package br.com.desafio.remessa.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CotacaoDolarDTO {
    @JsonProperty("cotacaoCompra")
    private BigDecimal cotacaoCompra;

    @JsonProperty("cotacaoVenda")
    private BigDecimal cotacaoVenda;

    @JsonProperty("dataHoraCotacao")
    private String dataHoraCotacao;

    // Getters e Setters
    public BigDecimal getCotacaoCompra() {
        return cotacaoCompra;
    }

    public void setCotacaoCompra(BigDecimal cotacaoCompra) {
        this.cotacaoCompra = cotacaoCompra;
    }

    public BigDecimal getCotacaoVenda() {
        return cotacaoVenda;
    }

    public void setCotacaoVenda(BigDecimal cotacaoVenda) {
        this.cotacaoVenda = cotacaoVenda;
    }

    public String getDataHoraCotacao() {
        return dataHoraCotacao;
    }

    public void setDataHoraCotacao(String dataHoraCotacao) {
        this.dataHoraCotacao = dataHoraCotacao;
    }
}
