package ru.geekbrains.spring.boot.april.market.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.boot.april.market.dtos.ProductDto;
import ru.geekbrains.spring.boot.april.market.error_handling.InvalidDataException;
import ru.geekbrains.spring.boot.april.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.spring.boot.april.market.models.Product;
import ru.geekbrains.spring.boot.april.market.services.CategoryService;
import ru.geekbrains.spring.boot.april.market.services.ProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping()
    public Page<ProductDto> getAllProducts(@RequestParam(name="p", defaultValue = "1") int page) {
        Page<Product> productsPage= productService.findPage(page-1, 10);
        Page<ProductDto> dtoPage = new PageImpl<>(productsPage.getContent().stream().map(ProductDto::new).collect(Collectors.toList()), productsPage.getPageable(), productsPage.getTotalElements());
        return dtoPage;
    }

    @GetMapping("/{id}")
//    public Product getProductById(@PathVariable Long id) {
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productService.findProductByID(id).orElseThrow(() ->
                new ResourceNotFoundException("Product doesn't exist with id = " + id));

        return new ProductDto(product);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        return productService.createNewProduct(productDto);

    }

    //
    @PutMapping()
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteProductById(@PathVariable Long id) {
        return productService.deleteProductByID(id);
    }
}
