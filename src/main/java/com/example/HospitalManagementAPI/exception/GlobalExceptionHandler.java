package com.example.HospitalManagementAPI.exception;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//for controller exception
@RestControllerAdvice // means we dont have to put try-catch in every controller method.
public class GlobalExceptionHandler {

    // class for not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    // class for bad reqs
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequest(BadRequestException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // class for conflict
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflict(ConflictException exception) {
        // call error response class
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(), // status
                exception.getMessage(), // message
                System.currentTimeMillis() //timestamp
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // class for general
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception exception) {
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
