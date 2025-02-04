package br.com.desafio.remessa.dtos;

import br.com.desafio.remessa.domains.carteira.TipoCarteira;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

public record CarteiraDTO(
                            String nomeCompleto,
                            String email,
                            String senha,
                            Long tipoCarteira,
                            String documento,
                            BigDecimal saldoBRL,
                            BigDecimal saldoUSD
) {}
