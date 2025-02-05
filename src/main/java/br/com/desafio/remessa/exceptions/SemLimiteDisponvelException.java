package br.com.desafio.remessa.exceptions;

public class SemLimiteDisponvelException extends RuntimeException {
    public SemLimiteDisponvelException() {
        super("Sem limite disponovel para transferencia");
    }
}
