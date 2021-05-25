package ru.geekbrains.spring.boot.april.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySource("classpath:sequred.properties")
public class AprilMarketApplication {
    // Домашнее задание:
    // 1. Сделайте кнопку оформить заказ, по нажатию на которую в базу должен быть сохранени заказ
    // ( без подвязки к пользователю, ** с подвязкой к пользователю)
    // 2. * Фронт: если пользователь вошел в систему, то в верхней панели отпечатать его имя
    public static void main(String[] args) {
        SpringApplication.run(AprilMarketApplication.class, args);

    }

}
