package br.com.desafio.remessa.exceptions;

public class ValorLimiteExcedidoException extends RuntimeException {
    public ValorLimiteExcedidoException(String mensagem) {
        super(mensagem);
    }
}
