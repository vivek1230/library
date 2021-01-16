package com.example.library.library.exceptions;


public abstract class LibraryException extends RuntimeException{
    public LibraryException(String message){
        super(message);
    }
}
