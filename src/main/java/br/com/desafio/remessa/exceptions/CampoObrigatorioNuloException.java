package br.com.desafio.remessa.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class CampoObrigatorioNuloException extends DataIntegrityViolationException {
    public CampoObrigatorioNuloException(String msg) {
        super("Campo obrigatorio estão nulo");
    }

}
