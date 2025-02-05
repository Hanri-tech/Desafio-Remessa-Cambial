package br.com.desafio.remessa.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class CampoObrigatorioNuloException extends DataIntegrityViolationException {
    public CampoObrigatorioNuloException() {
        super("Campo obrigatorio estão nulo");
    }

}
