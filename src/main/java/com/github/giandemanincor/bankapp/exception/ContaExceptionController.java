package com.github.giandemanincor.bankapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ContaExceptionController {

    @ExceptionHandler(ContaExistException.class)
    public ResponseEntity<Object> exception(ContaExistException exception) {
        MensagemErro erro = new MensagemErro(exception.getCodigo(), exception.getMensagem());
        HttpStatus httpStatus = HttpStatus.valueOf(exception.getCodigo());
        return new ResponseEntity<>(erro, httpStatus);
    }
}
