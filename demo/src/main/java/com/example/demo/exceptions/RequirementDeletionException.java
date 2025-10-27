package com.example.demo.exceptions;
 
public class RequirementDeletionException  extends RuntimeException{
    public RequirementDeletionException(String message){
        super(message);
       }
}