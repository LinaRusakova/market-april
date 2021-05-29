package ru.geekbrains.spring.boot.april.market.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.spring.boot.april.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.spring.boot.april.market.models.Order;
import ru.geekbrains.spring.boot.april.market.models.OrderItem;
import ru.geekbrains.spring.boot.april.market.models.User;
import ru.geekbrains.spring.boot.april.market.repositories.OrderRepository;
import ru.geekbrains.spring.boot.april.market.utils.Cart;

import java.util.Collection;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    @Transactional
    public void createNewOrder(String address, String phone, Cart cart) {
        Order order = new Order();
        order.addItemsFromCart(cart);
        log.info("--The request to inject Cart to New Order was received  successfully.--");
        log.info(String.valueOf(order.getOrderSum()));
        log.info(String.valueOf(order.getOrderItems().stream().map(m -> m.getProduct().getTitle()).collect(Collectors.toList())));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User currentUser = userService.findByUsername(name).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist"));
        log.info("--The request to inject Current User to New Order was received  successfully.--  ==" + name);

        order.setAddress(address);
        log.info(address);
        order.setPhoneNumber(phone);
        log.info(phone);
        order.setUser(currentUser);

        Collection<Order> orderCollection = currentUser.getOrders();
        orderCollection.add(order);

        orderRepository.save(order);
        order.getOrderItems().forEach(orderItem -> orderItem.setOrder(order));
        order.getOrderItems().forEach(orderItem -> orderItem.setCreatedAt(order.getCreatedAt()));
        log.info("--The request to create New Order was saved into database successfully.--");


    }
}
