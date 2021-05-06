package ru.geekbrains.spring.boot.april.market.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.spring.boot.april.market.dtos.ProductDto;
import ru.geekbrains.spring.boot.april.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.spring.boot.april.market.models.Category;
import ru.geekbrains.spring.boot.april.market.models.Product;
import ru.geekbrains.spring.boot.april.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;


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

    @Transactional
    public ProductDto createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() ->
                new ResourceNotFoundException("Category doesn't exist with id = " + productDto.getCategoryTitle()));
        product.setCategory(category);
        productRepository.save(product);
        return new ProductDto(product);
    }
}
