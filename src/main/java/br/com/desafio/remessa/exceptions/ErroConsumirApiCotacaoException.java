package br.com.desafio.remessa.exceptions;

public class ErroConsumirApiCotacaoException extends RuntimeException {

    public ErroConsumirApiCotacaoException() {
        super("Não foi possivel buscar a Cotação");
    }

}
