package ru.geekbrains.spring.boot.april.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.boot.april.market.models.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
