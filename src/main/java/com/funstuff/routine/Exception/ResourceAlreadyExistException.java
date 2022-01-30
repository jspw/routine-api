package com.funstuff.routine.Exception;

public class ResourceAlreadyExistException extends RuntimeException{
    public ResourceAlreadyExistException(){
        super();
    }

    public ResourceAlreadyExistException(String str){
        super(str);
    }

    public ResourceAlreadyExistException(String message, Throwable cause){
        super(message,cause);
    }

    public ResourceAlreadyExistException(Throwable cause){
        super(cause);
    }
}
