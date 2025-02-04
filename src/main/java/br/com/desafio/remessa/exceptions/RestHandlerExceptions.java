package br.com.desafio.remessa.exceptions;

import br.com.desafio.remessa.dtos.RetornoErroExceptionsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestHandlerExceptions {

    @ExceptionHandler(CampoObrigatorioNuloException.class)
    public ResponseEntity<?> campoObrigatorioNuloExceptions(CampoObrigatorioNuloException e) {
        return ResponseEntity.badRequest().body(new RetornoErroExceptionsDTO(e.getMessage()));
    }


}
