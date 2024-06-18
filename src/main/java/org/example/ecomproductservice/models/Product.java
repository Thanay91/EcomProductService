package org.example.ecomproductservice.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Getter
@Setter
public class Product extends BaseModel {
    private String name;
    private String description;
    private String imageURL;

    //P  :  R
    //1  :  M
    //1  : 1

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "Rating_id")
    private Rating rating;
    private double price;

}
