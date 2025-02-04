package br.com.desafio.remessa.domains.carteira;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TipoCarteira {

    @Id
    private Long id;
    private String descricao;

    public TipoCarteira(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public TipoCarteira() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
