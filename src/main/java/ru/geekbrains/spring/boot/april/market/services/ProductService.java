package ru.geekbrains.spring.boot.april.market.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.boot.april.market.models.Product;
import ru.geekbrains.spring.boot.april.market.repositories.ProductRepository;

import javax.persistence.Entity;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findProductByID(Long id) {
        return productRepository.findById(id);
    }

    public Product putProduct(Product product) {
        Product product1 = findProductByID(product.getId()).get();
        product1.setPrice(product.getPrice());
        product1.setTitle(product.getTitle());
        return productRepository.save(product1);
    }


    public boolean deleteProductByID(Long id) {

        productRepository.delete(findProductByID(id).get());
        return productRepository.findById(id).isEmpty() ? true : false;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
