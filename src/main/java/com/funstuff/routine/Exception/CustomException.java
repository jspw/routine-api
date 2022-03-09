package com.funstuff.routine.Exception;

import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class CustomException extends RuntimeException{
    private String errorMessage;
    private HttpStatus httpStatus;

    public CustomException(String str,HttpStatus httpStatus){
        super(str);
        this.errorMessage = str;
        this.httpStatus = httpStatus;
    }
}
