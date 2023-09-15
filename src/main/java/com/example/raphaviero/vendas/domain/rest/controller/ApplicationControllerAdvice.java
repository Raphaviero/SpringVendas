package com.example.raphaviero.vendas.domain.rest.controller;

import com.example.raphaviero.vendas.domain.rest.ApiErrors;
import com.example.raphaviero.vendas.exception.BusinessRuleException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBusinessRulesException(BusinessRuleException ex){
        String messageErro = ex.getMessage();
        return new ApiErrors(messageErro);
    }
}
