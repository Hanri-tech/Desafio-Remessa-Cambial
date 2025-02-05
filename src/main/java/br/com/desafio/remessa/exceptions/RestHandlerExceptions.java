package br.com.desafio.remessa.exceptions;

import br.com.desafio.remessa.dtos.RetornoErroExceptionsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestHandlerExceptions {

    @ExceptionHandler(CampoObrigatorioNuloException.class)
    public ResponseEntity<?> campoObrigatorioNuloException(CampoObrigatorioNuloException e) {
        return ResponseEntity.badRequest().body(new RetornoErroExceptionsDTO(e.getMessage()));
    }

    @ExceptionHandler(CarteiraNaoEncontradaException.class)
    public ResponseEntity<?> carteiraNaoEncontradaException(CarteiraNaoEncontradaException e) {
        return ResponseEntity.badRequest().body(new RetornoErroExceptionsDTO(e.getMessage()));
    }

    @ExceptionHandler(DadosCateiraUnicoExisteException.class)
    public ResponseEntity<?> dadosCateiraUnicoException(DadosCateiraUnicoExisteException e) {
        return ResponseEntity.internalServerError().body(new RetornoErroExceptionsDTO(e.getMessage()));
    }

    @ExceptionHandler(EnviarRemessaPagadorParaPagadorException.class)
    public ResponseEntity<?> enviarRemessaPagadorException(EnviarRemessaPagadorParaPagadorException e) {
        return ResponseEntity.internalServerError().body(new RetornoErroExceptionsDTO(e.getMessage()));
    }

    @ExceptionHandler(ErroBuscarUltimaCotacaoException.class)
    public ResponseEntity<?> erroBuscarUltimaCotacaoException(ErroBuscarUltimaCotacaoException e) {
        return ResponseEntity.internalServerError().body(new RetornoErroExceptionsDTO(e.getMessage()));
    }

    @ExceptionHandler(ErroConsumirApiCotacaoException.class)
    public ResponseEntity<?> erroConsumirApiCotacaoException(ErroConsumirApiCotacaoException e) {
        return ResponseEntity.internalServerError().body(new RetornoErroExceptionsDTO(e.getMessage()));
    }

    @ExceptionHandler(SemLimiteDisponvelException.class)
    public ResponseEntity<?> semLimiteDisponvelException (SemLimiteDisponvelException e) {
        return ResponseEntity.badRequest().body(new RetornoErroExceptionsDTO(e.getMessage()));
    }

    @ExceptionHandler(TipoUsuarioCarteiroNaoEncontradoException.class)
    public ResponseEntity<?> tipoUsuarioCarteiroNaoEncontradoException(TipoUsuarioCarteiroNaoEncontradoException e) {
        return ResponseEntity.internalServerError().body(new RetornoErroExceptionsDTO(e.getMessage()));
    }

    @ExceptionHandler(ValorLimiteExcedidoException.class)
    public ResponseEntity<?> valorLimiteExcedidoException(ValorLimiteExcedidoException e) {
        return ResponseEntity.internalServerError().body(new RetornoErroExceptionsDTO(e.getMessage()));
    }


}
