package com.franklin.deck.utils;

import com.franklin.deck.exceptions.BadRequestException;
import com.franklin.deck.exceptions.InvalidActionException;
import com.franklin.deck.exceptions.NotFoundException;
import com.franklin.deck.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleException(NotFoundException ex) {
        ApiError errorResponse = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleException(BadRequestException ex) {
        ApiError errorResponse = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiError> handleException(UnauthorizedException ex) {
        ApiError errorResponse = new ApiError(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiError> handleException(InvalidActionException ex) {
        ApiError errorResponse = new ApiError(HttpStatus.OK, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
}
