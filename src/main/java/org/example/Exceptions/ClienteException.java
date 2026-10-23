package org.example.Exceptions;

public class ClienteException extends RuntimeException{

    public ClienteException (String mensagem){
        super(mensagem);
    }
}
