package br.com.desafio.remessa.domain.carteira;

public enum TipoCarteiraEnum {
    CPF(1, "CPF"),
    CNPJ(2, "CNPJ");

    private int id;
    private String descricao;

    TipoCarteiraEnum(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public TipoCarteira getTipoCarteira() {
        return new TipoCarteira(id, descricao);
    }

}
