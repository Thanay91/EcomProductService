package org.example.ecomproductservice.services;

import org.example.ecomproductservice.dtos.ProductRequestDTO;
import org.example.ecomproductservice.exceptions.EmptyDatabaseException;
import org.example.ecomproductservice.exceptions.ErrorCopyingRequestDTO;
import org.example.ecomproductservice.exceptions.ErrorFindingFilteredProduct;
import org.example.ecomproductservice.exceptions.ProductNotFoundException;
import org.example.ecomproductservice.models.Product;
import org.example.ecomproductservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.JpaEntityGraph;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAllProducts(String sortType, Integer limit) throws EmptyDatabaseException, ErrorFindingFilteredProduct {
        if(sortType==null && limit==null){
            Optional<List<Product>> productOptional = productRepository.getAllBy();
            if(productOptional.get().size()==0){
                throw new EmptyDatabaseException("The Database is currently empty");
            }
            return productOptional.get();
        }
        Sort sort = Sort.by("id").ascending();
        limit = (limit==null)? 1000:limit;
        if(sortType!=null&& sortType.equals("desc")){
            sort = Sort.by("id").descending();
        }
        Pageable pageable = PageRequest.of(0,limit, sort);
        Optional<List<Product>> sortedAndLimitedProductsOptional = productRepository.findAllBy(pageable);
        if(sortedAndLimitedProductsOptional.get().size()==0){
            throw new ErrorFindingFilteredProduct("Product List is empty for applied filter and sort");
        }
        List<Product> filteredProducts = sortedAndLimitedProductsOptional.get();
        return filteredProducts;
    }

    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("This product with "+ id +" is not available");
        }
        return optionalProduct.get();
    }

    public Product addNewProduct(ProductRequestDTO requestDTO) throws ErrorCopyingRequestDTO {
        Product toBeSavedProduct = convertReauestDTOToProduct(requestDTO, new Product());
        toBeSavedProduct.setCreatedAt(new Date());
        toBeSavedProduct.setModifiedAt(new Date());
        Product savedProduct = productRepository.save(toBeSavedProduct);
        return savedProduct;
    }

    public Product modifyProduct(ProductRequestDTO requestDTO, Long id) throws ProductNotFoundException, ErrorCopyingRequestDTO {
        Optional<Product> optionalExistingProduct = productRepository.findById(id);
        if(optionalExistingProduct.isEmpty()){
            throw new ProductNotFoundException("The product you are trying to modify does not exist");
        }
        Product existingProduct = optionalExistingProduct.get();
        Product modifiedProduct = convertReauestDTOToProduct(requestDTO, existingProduct);
        modifiedProduct.setModifiedAt(new Date());
        Product savedProduct = productRepository.save(modifiedProduct);
        return savedProduct;
    }

    private Product convertReauestDTOToProduct(ProductRequestDTO requestDTO, Product target) throws ErrorCopyingRequestDTO {
        Product product = target;
        Field[] fields = requestDTO.getClass().getDeclaredFields();
        for(Field field:fields){
            try {
                field.setAccessible(true); // Allow accessing private fields
                Object value = field.get(requestDTO);
                if (value != null) {
                    Field productField = Product.class.getDeclaredField(field.getName());
                    productField.setAccessible(true);
                    productField.set(product, value);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new ErrorCopyingRequestDTO("Some fields could not be set");
            }

        }
        return product;
    }
}
