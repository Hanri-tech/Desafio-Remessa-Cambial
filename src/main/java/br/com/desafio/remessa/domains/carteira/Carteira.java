package br.com.desafio.remessa.domains.carteira;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
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

    @Column(nullable = false)
    private BigDecimal saldoBRL = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal saldoUSD = BigDecimal.ZERO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoCarteira getTipoCarteira() {
        return tipoCarteira;
    }

    public void setTipoCarteira(TipoCarteira tipoCarteira) {
        this.tipoCarteira = tipoCarteira;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public BigDecimal getSaldoBRL() {
        return saldoBRL;
    }

    public void setSaldoBRL(BigDecimal saldoBRL) {
        this.saldoBRL = saldoBRL;
    }

    public BigDecimal getSaldoUSD() {
        return saldoUSD;
    }

    public void setSaldoUSD(BigDecimal saldoUSD) {
        this.saldoUSD = saldoUSD;
    }
}
