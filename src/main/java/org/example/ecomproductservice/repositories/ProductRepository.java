package org.example.ecomproductservice.repositories;

import org.example.ecomproductservice.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public Optional<List<Product>> getAllBy();

    public Optional<List<Product>> findAllBy(Pageable pageable);

    public Optional<Product> findById(Long id);

    public Product save(Product product);
}
