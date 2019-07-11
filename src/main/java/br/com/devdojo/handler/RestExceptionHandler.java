package br.com.devdojo.handler;

import br.com.devdojo.error.ResourceNotFoundDetails;
import br.com.devdojo.error.ResourceNotFoundException;
import br.com.devdojo.error.ValidationErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        ResourceNotFoundDetails rfnDetails = ResourceNotFoundDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not found")
                .detail(e.getMessage())
                .developerMessage(e.getClass().getName())
                .build();
        return new ResponseEntity<>(rfnDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.toList());
        List<String> fieldsMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        List<String> objectNames = fieldErrors.stream().map(FieldError::getObjectName).collect(Collectors.toList());

//        List<String> fields2 = fieldErrors.stream().map(FieldError::getField).collect(Collectors.toList());
//
//        List<Object> fields3 = fieldErrors.stream().map(item ->
//                new Object() {
//                    String field = item.getField();
//                    String objectName = item.getObjectName();
//                    String defaultMessage = item.getDefaultMessage();
//                }
//        ).collect(Collectors.toList());
//
//        Object x = fields3.get(0);

        ValidationErrorDetails val = ValidationErrorDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Field validation error")
                .detail("Field validation error")
                .developerMessage(e.getClass().getName())
                .field(fields)
                .fieldMessage(fieldsMessages)
                .objectNames(objectNames)
                .build();
        return new ResponseEntity<>(val, HttpStatus.BAD_REQUEST);
    }
}
