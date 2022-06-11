package com.example.onlineshopapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PieceNotFoundException extends RuntimeException{
    public PieceNotFoundException(String msg) {
        super(msg);
    }
}
