package br.com.desafio.remessa.exceptions;

public class ErroBuscarUltimaCotacaoException extends RuntimeException {
    public ErroBuscarUltimaCotacaoException() {
        super("Erro ao buscar ultima Cotacao");
    }
}
