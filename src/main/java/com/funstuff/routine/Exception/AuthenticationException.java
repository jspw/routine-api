package com.funstuff.routine.Exception;

public class AuthenticationException extends RuntimeException{
    public AuthenticationException(){
        super();
    }

    public AuthenticationException(String str){
        super(str);
    }

    public AuthenticationException(String message, Throwable cause){
        super(message,cause);
    }

    public AuthenticationException(Throwable cause){
        super(cause);
    }
}
