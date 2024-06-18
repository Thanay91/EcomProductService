package org.example.ecomproductservice.controllerAdvisor;

import org.example.ecomproductservice.dtos.ErrorResponseDTO;
import org.example.ecomproductservice.exceptions.EmptyDatabaseException;
import org.example.ecomproductservice.exceptions.ErrorCopyingRequestDTO;
import org.example.ecomproductservice.exceptions.ErrorFindingFilteredProduct;
import org.example.ecomproductservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(EmptyDatabaseException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmptyDatabaseException(EmptyDatabaseException e){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setError_message(e.getMessage());
        errorResponseDTO.setError_thrown_from("This Error is thrown from Global level");
        ResponseEntity responseEntity = new ResponseEntity(errorResponseDTO, HttpStatus.NOT_FOUND);
        return responseEntity;
    }


    @ExceptionHandler(ErrorFindingFilteredProduct.class)
    public ResponseEntity<ErrorResponseDTO> handleErrorFindingFilteredProduct(ErrorFindingFilteredProduct e){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setError_message(e.getMessage());
        errorResponseDTO.setError_thrown_from("This Error is thrown from Global level");
        ResponseEntity responseEntity = new ResponseEntity(errorResponseDTO, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductNotFoundException(ProductNotFoundException e){
        ErrorResponseDTO responseDTO = new ErrorResponseDTO();
        responseDTO.setError_message(e.getMessage());
        responseDTO.setError_thrown_from("This Error is thrown from Global level");
        ResponseEntity responseEntity = new ResponseEntity(responseDTO, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(ErrorCopyingRequestDTO.class)
    public ResponseEntity<ErrorResponseDTO> handleErrorCopyingRequestDTO(ErrorCopyingRequestDTO e){
        ErrorResponseDTO responseDTO = new ErrorResponseDTO();
        responseDTO.setError_message(e.getMessage());
        responseDTO.setError_thrown_from("This Error is thrown from Global level");
        ResponseEntity responseEntity = new ResponseEntity(responseDTO, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

}
