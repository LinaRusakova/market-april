package ru.geekbrains.spring.boot.april.market.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.boot.april.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.spring.boot.april.market.models.Category;
import ru.geekbrains.spring.boot.april.market.models.Order;
import ru.geekbrains.spring.boot.april.market.models.User;
import ru.geekbrains.spring.boot.april.market.repositories.CategoryRepository;
import ru.geekbrains.spring.boot.april.market.repositories.OrderRepository;
import ru.geekbrains.spring.boot.april.market.utils.Cart;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    public void createNewOrder(String address, String phone, Cart cart) {
        Order order = new Order(cart);
        log.info("--The request to inject Cart to New Order was received  successfully.--" );

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User currentUser = userService.findByUsername(name).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist"));
        log.info("--The request to inject Current User to New Order was received  successfully.--  ==" + name );
        order.setAddress(address);
        order.setPhoneNumber(phone);
        order.setUser(currentUser);

        orderRepository.save(order);
        log.info("--The request to create New Order was saved into database successfully.--" );

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
