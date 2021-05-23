package ru.geekbrains.spring.boot.april.market.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
public class OrderItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prouct_id" )
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_per_product")
    private BigDecimal pricePerProduct;

    @Column(name="price")
    private BigDecimal price;


    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public OrderItem(Product product) {
        this.product=product;
        this.quantity=1;
        this.pricePerProduct = product.getPrice();
        this.price=product.getPrice();
    }

    public void incrementQuantity() {
        this.quantity++;
        this.price= this.pricePerProduct.multiply(new BigDecimal(this.quantity));
    }

    public void decrementQuantity() {
        this.quantity--;
        this.price= this.pricePerProduct.multiply(new BigDecimal(this.quantity));
    }

}
