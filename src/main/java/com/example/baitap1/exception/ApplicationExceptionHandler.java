package com.example.baitap1.exception;

import com.example.baitap1.entity.ResponseObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerInvalidArgument(MethodArgumentNotValidException ex){
    Map<String, String> errorMap = new HashMap<String,String>();
    ex.getBindingResult().getFieldErrors().forEach(error -> {
        errorMap.put(error.getField(),error.getDefaultMessage());
    });
    ResponseObject<?> responseObject = new ResponseObject<>(false, "valid Error" , errorMap);
    return new ResponseEntity(responseObject,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<?> handlerNotFoundException(NotFoundException ex, WebRequest req){
        Map<String, String> errorMap = new HashMap<String,String>();
        errorMap.put("Error", ex.getMessage());
        ResponseObject<?> responseObject = new ResponseObject<>(false, ex.getMessage(), errorMap);
        return new ResponseEntity(responseObject, HttpStatus.BAD_REQUEST);
    }

}


//public class ValidationHandler extends ResponseEntityExceptionHandler {
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
////        return super.handleMethodArgumentNotValid(ex, headers, status, request);
//        Map<String,String> errors = new HashMap<String,String>();
//        ex.getBindingResult().getAllErrors().forEach((error) ->{
//            String fieldName = ((FieldError) error).getField();
//            String message = error.getDefaultMessage();
//            errors.put(fieldName,message);
//
//        });
//        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
//    }
//}