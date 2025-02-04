package br.com.desafio.remessa.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class DadosCateiraUnicoExisteException extends DataIntegrityViolationException {
    public DadosCateiraUnicoExisteException() {
        super("Email ou Documento já existe");
    }
}
