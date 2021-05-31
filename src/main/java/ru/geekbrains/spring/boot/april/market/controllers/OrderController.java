package ru.geekbrains.spring.boot.april.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import ru.geekbrains.spring.boot.april.market.dtos.OrderDto;
import ru.geekbrains.spring.boot.april.market.models.Order;
import ru.geekbrains.spring.boot.april.market.models.User;
import ru.geekbrains.spring.boot.april.market.services.OrderService;
import ru.geekbrains.spring.boot.april.market.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;


    @PostMapping()
    public void createOrderForCurrentUser(@RequestParam(name = "address", defaultValue = "Moscow, Kremlin") String address, @RequestParam(name = "phone", defaultValue = "112") String phone) {
        log.info("--The request to create New Order was received by Server successfully.--");
        orderService.createOrderForCurrentUser(address, phone);
    }

    @GetMapping
    @Transactional
    public List<OrderDto> getAllOrdersForCurrentUser(Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        return orderService.findAllByUser(user).stream().map(OrderDto::new).collect(Collectors.toList());
    }

}
