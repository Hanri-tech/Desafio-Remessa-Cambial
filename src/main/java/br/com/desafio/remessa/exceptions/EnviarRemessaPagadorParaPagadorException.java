package br.com.desafio.remessa.exceptions;

public class EnviarRemessaPagadorParaPagadorException extends RuntimeException {
    public EnviarRemessaPagadorParaPagadorException() {
        super("Transação inválida: o remetente não pode transferir para sua própria conta");
    }
}
