package com.example.demo.exceptions;
 
public class DuplicateRequirementException extends RuntimeException {
 
    public DuplicateRequirementException(String message)
    {
        super(message);
    }
}