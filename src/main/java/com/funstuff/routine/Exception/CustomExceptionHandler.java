package com.funstuff.routine.Exception;


import com.funstuff.routine.payload.response.ApiException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<?> handleInvalidInputException(Exception e){
        return buildResponseEntity(new ApiException(HttpStatus.BAD_REQUEST,e.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(Exception ex){
        return buildResponseEntity(new ApiException(HttpStatus.NOT_FOUND,ex.getMessage()));
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<?> handleResourceAlreadyExists(Exception ex){
        return buildResponseEntity(new ApiException(HttpStatus.BAD_REQUEST,ex.getMessage()));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomExceptions(CustomException ex){
        return buildResponseEntity(new ApiException(ex.getHttpStatus(),ex.getErrorMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(Exception ex){
        return buildResponseEntity(new ApiException(HttpStatus.UNAUTHORIZED,"Please login"));
    }


    private ResponseEntity<ApiException> buildResponseEntity(ApiException apiException){
        return new ResponseEntity<>(apiException,apiException.getHttpStatus());
    }

}
