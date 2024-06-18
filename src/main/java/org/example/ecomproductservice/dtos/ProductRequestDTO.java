package org.example.ecomproductservice.dtos;


import lombok.Getter;
import lombok.Setter;
import org.example.ecomproductservice.models.Rating;

@Getter
@Setter

public class ProductRequestDTO {
    private String name;
    private String description;
    private String imageURL;
    private Rating rating;
    private double price;
}
