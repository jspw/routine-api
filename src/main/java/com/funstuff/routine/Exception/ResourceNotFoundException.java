package com.funstuff.routine.Exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super();
    }

    public ResourceNotFoundException(String str){
        super(str);
    }

    public ResourceNotFoundException(String message, Throwable cause){
        super(message,cause);
    }

    public ResourceNotFoundException(Throwable cause){
        super(cause);
    }
}
