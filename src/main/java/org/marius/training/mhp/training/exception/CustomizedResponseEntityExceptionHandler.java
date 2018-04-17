package org.marius.training.mhp.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

    public CustomizedResponseEntityExceptionHandler(){
        System.out.println("dsadsadsadsadsa\n\n\n");
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException ex) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n" + ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body();
    }
}
