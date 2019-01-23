package com.stackroute.muzix.exceptions;

public class TrackalreadyExistsException extends Exception{
    public String message;

   // public TrackalreadyExistsException(){}
    public TrackalreadyExistsException(String message) {
        super(message);
        this.message=message;
    }
}
