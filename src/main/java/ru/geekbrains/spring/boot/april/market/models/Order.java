package ru.geekbrains.spring.boot.april.market.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.geekbrains.spring.boot.april.market.utils.Cart;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;


@Entity
@Data
@NoArgsConstructor
@Table(name = "users_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "contact_phone")
    private String phoneNumber;

    @Column(name = "order_sum")
    private BigDecimal orderSum;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    private Collection<OrderItem> orderItems;


    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "orders")
    private User user;


    public Order(Cart cart) {
        this.orderItems = cart.getItems();
        this.orderSum = cart.getSum();
    }
}
