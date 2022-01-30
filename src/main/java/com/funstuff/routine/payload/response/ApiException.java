package com.funstuff.routine.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Data
public class ApiException {
    private HttpStatus httpStatus;
    private String errorMessage;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-mm-yyyy hh:mm:ss")
    private LocalDateTime date;

    private ApiException(){
        this.date = LocalDateTime.now();
    }

    public ApiException(HttpStatus httpStatus, String errorMessage) {
        this();
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;

    }
}
