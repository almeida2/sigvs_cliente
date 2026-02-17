package edu.fatec.sigvs_cliente.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseAPI> handleIllegalArgumentException(IllegalArgumentException ex) {
        ResponseAPI response = new ResponseAPI(ex.getMessage(), "400", null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseAPI> handleGeneralException(Exception ex) {
        ResponseAPI response = new ResponseAPI("Erro interno no servidor: " + ex.getMessage(), "500", null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
