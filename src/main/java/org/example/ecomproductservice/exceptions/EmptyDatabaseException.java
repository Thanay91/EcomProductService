package org.example.ecomproductservice.exceptions;

import java.security.PublicKey;

public class EmptyDatabaseException extends Exception{

    public EmptyDatabaseException(String message){
        super(message);
    }
}
