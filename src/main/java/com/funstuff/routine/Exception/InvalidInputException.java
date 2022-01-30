package com.funstuff.routine.Exception;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(){
        super();
    }

    public InvalidInputException(String str){
        super(str);
    }

    public InvalidInputException(String message,Throwable cause){
        super(message,cause);
    }

    public InvalidInputException(Throwable cause){
        super(cause);
    }
}
