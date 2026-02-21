package com.banking.exception;


import com.banking.dto.error.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError>
    handleMethodArgumentNotValidException(MethodArgumentNotValidException
                                                  methodArgumentNotValidException
            , HttpServletRequest request){

        Map<String, String> errors = new HashMap<>();

        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(fe ->
                errors.put(fe.getField(), fe.getDefaultMessage())
        );


        return ResponseEntity.status(400).body(ApiError.builder()
                .message("Validation Exception")
                .path(request.getRequestURI())
                .validationError(errors.isEmpty()?null:errors)
                .time(new Date().getTime())
                .build());

    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllException(Exception
                                                                            exception
            , HttpServletRequest request){

       log.error("Unexpected error message: {}",exception.getMessage());
        return ResponseEntity.status(500).body(
                ApiError.builder()
                        .time(new Date().getTime())
                        .message("Unexpected error")
                        .path(request.getRequestURI())
                        .build()
        );
    }


}
