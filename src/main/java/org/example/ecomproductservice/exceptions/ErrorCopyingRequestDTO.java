package org.example.ecomproductservice.exceptions;

import org.example.ecomproductservice.dtos.ErrorResponseDTO;

public class ErrorCopyingRequestDTO extends Exception{

    public ErrorCopyingRequestDTO(String message){
        super(message);
    }
}
