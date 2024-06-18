package org.example.ecomproductservice.controller;

import org.example.ecomproductservice.dtos.ProductRequestDTO;
import org.example.ecomproductservice.exceptions.EmptyDatabaseException;
import org.example.ecomproductservice.exceptions.ErrorCopyingRequestDTO;
import org.example.ecomproductservice.exceptions.ErrorFindingFilteredProduct;
import org.example.ecomproductservice.exceptions.ProductNotFoundException;
import org.example.ecomproductservice.models.Product;
import org.example.ecomproductservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    private ResponseEntity responseEntity;
    private Product product;
    private ProductRequestDTO requestDTO;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String sort,
                                                        @RequestParam(required = false) Integer limit) throws EmptyDatabaseException, ErrorFindingFilteredProduct {

        List<Product> products =  productService.getAllProducts(sort, limit);
        responseEntity = new ResponseEntity(products, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping()
    public Product addNewProduct( @RequestBody ProductRequestDTO requestDTO) throws ErrorCopyingRequestDTO {
        return productService.addNewProduct(requestDTO);

    }

    @PatchMapping("/{id}")
    public Product modifyProduct(@PathVariable Long id, @RequestBody ProductRequestDTO requestDTO) throws ErrorCopyingRequestDTO, ProductNotFoundException {
        return productService.modifyProduct(requestDTO,id);
    }


}
