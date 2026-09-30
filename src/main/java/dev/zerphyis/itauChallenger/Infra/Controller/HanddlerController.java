package dev.zerphyis.itauChallenger.Infra.Controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HanddlerController {

    private static final Logger logger = LoggerFactory.getLogger(HanddlerController.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> handleBusinessRules(IllegalArgumentException ex) {
        logger.warn("Transação rejeitada por regra de negócio: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Void> handleJsonParseError(HttpMessageNotReadableException ex) {
        logger.warn("Requisição malformada recebida: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleGenericError(Exception ex) {
        logger.error("Erro interno não esperado", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
