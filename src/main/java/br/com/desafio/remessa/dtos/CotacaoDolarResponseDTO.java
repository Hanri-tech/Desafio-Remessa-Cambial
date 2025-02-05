package br.com.desafio.remessa.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CotacaoDolarResponseDTO {
    @JsonProperty("@odata.context")
    private String odataContext;

    private List<CotacaoDolarDTO> value;

    // Getters e Setters
    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<CotacaoDolarDTO> getValue() {
        return value;
    }

    public void setValue(List<CotacaoDolarDTO> value) {
        this.value = value;
    }
}
