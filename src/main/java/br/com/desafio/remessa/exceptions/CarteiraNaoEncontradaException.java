package br.com.desafio.remessa.exceptions;

public class CarteiraNaoEncontradaException extends RuntimeException{
    public CarteiraNaoEncontradaException(){
        super("Usuario da carteira nao encontrada");
    }
}
