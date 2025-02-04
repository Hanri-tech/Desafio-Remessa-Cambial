package br.com.desafio.remessa.domains.carteira;

public enum TipoCarteiraEnum {
    CPF(1L, "CPF"),
    CNPJ(2L, "CNPJ");

    private Long id;
    private String descricao;

    TipoCarteiraEnum(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
