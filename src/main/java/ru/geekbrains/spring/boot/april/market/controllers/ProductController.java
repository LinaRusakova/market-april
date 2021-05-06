package ru.geekbrains.spring.boot.april.market.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.boot.april.market.dtos.ProductDto;
import ru.geekbrains.spring.boot.april.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.spring.boot.april.market.models.Product;
import ru.geekbrains.spring.boot.april.market.services.CategoryService;
import ru.geekbrains.spring.boot.april.market.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping()
    public List<ProductDto> getAllProducts() {
        return productService.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
//    public Product getProductById(@PathVariable Long id) {
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productService.findProductByID(id).orElseThrow(() ->
                new ResourceNotFoundException("Product doesn't exist with id = " + id));

        return new ProductDto(product);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto) {
        return productService.createNewProduct(productDto);

    }
//
    @PutMapping()
    public Product putProductById(@RequestBody Product product) {
        return productService.putProduct(product);
    }

    @DeleteMapping("/{id}")
    public boolean deleteProductById(@PathVariable Long id) {
        return productService.deleteProductByID(id);
    }
}
