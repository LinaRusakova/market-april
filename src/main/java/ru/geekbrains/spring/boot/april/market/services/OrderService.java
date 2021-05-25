package ru.geekbrains.spring.boot.april.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.boot.april.market.models.Category;
import ru.geekbrains.spring.boot.april.market.models.Order;
import ru.geekbrains.spring.boot.april.market.repositories.CategoryRepository;
import ru.geekbrains.spring.boot.april.market.repositories.OrderRepository;
import ru.geekbrains.spring.boot.april.market.utils.Cart;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;

    public void create(Cart cart) {

    }
//
//    public Optional<Category> findById(Long id) {
//        return categoryRepository.findById(id);
//    }
//
//    public Optional<Category> findByTitle(String title) {
//        return categoryRepository.findByTitle(title);
//    }
}
