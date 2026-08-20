package com.uninter.ads.back_end.exception;

import java.util.List;

/*import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;*/

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
	// 1. ERROS DE VALIDAÇÃO (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        System.out.println("erros '400'");
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }   
    
    
    // 2. ERROS DE BANCO (unique, not null, etc.)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MensagemErro> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new MensagemErro("Já existe um registro com este nome!"));
    }
    
    
    
    
    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
    
    // Record para mensagem simples
    private record MensagemErro(String mensagem) {
    }
    

}


// Tratamento específico para erros de validação
/*@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new LinkedHashMap<>();
    
    ex.getBindingResult().getAllErrors().forEach(error -> {
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        errors.put(fieldName, errorMessage);
    });
    
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.BAD_REQUEST.value());
    body.put("error", "Erro de validação");
    body.put("errors", errors);
    
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
}*/   




// Para erros de unique (nome duplicado)
/*@ExceptionHandler(DataIntegrityViolationException.class)
public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.CONFLICT.value());
    body.put("error", "Conflito de dados");
    body.put("message", "Já existe um registro com este nome");
    
    return new ResponseEntity<>(body, HttpStatus.CONFLICT);
}*/