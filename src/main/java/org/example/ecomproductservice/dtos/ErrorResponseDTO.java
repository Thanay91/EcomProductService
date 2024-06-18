package org.example.ecomproductservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponseDTO {
    String error_message;
    String error_thrown_from;

}
