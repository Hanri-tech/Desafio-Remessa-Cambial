package br.com.desafio.remessa.exceptions;

public class TipoUsuarioCarteiroNaoEncontradoException extends RuntimeException {
    public TipoUsuarioCarteiroNaoEncontradoException() {
        super("Tipo do Usuario Nao Encontrado");
    }
}
