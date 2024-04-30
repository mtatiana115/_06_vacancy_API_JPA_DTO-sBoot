package com.riwi.vacancies.controllers.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.riwi.vacancies.utils.dto.errors.BaseErrorResponse;
import com.riwi.vacancies.utils.dto.errors.ErrorResponse;
import com.riwi.vacancies.utils.exceptions.IdNotFoundExeption;

//@RestControllerAdvice  para escuchar en todo momento cuando el error se active y @ResponseStatus para devolver el status 400

/**
 * RestControllerAdvice = controlador de errores
 */
/**
 * ResponseStatus = Statuse de error
 */
@RestControllerAdvice
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestController {

    /**
     * Para especificar cuando se va a disparar este metodo utilizamos
     * la notación ExcepetionHandler
     */
    @ExceptionHandler(IdNotFoundExeption.class)
    public BaseErrorResponse handleIdNotFound(IdNotFoundExeption e){

        return ErrorResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();

                //ErrorResponse error = new ErrorResponse()
                //error.setMessage(e.getMessage());
                //error.setStatus(HttpStatus.BAD_REQUEST.name());
                //error.code(HttpStatus.BAD_REQUEST.value());
    }
}
