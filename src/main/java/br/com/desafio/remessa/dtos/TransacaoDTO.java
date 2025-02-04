package br.com.desafio.remessa.dtos;

import java.math.BigDecimal;

public record TransacaoDTO(
        Long pagador,
        Long recebedor,
        BigDecimal vlrTransferencia
) {
}
