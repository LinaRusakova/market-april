package ru.geekbrains.spring.boot.april.market.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
