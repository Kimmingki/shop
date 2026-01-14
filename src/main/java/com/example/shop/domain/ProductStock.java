package com.example.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@Entity
@Table(name = "product_stock")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductStock {

    @Id
    @Column(name="product_id")
    private Long productId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name="quantity", nullable = false)
    private int quantity;

    @Column(name="updated_at", nullable = false)
    private Instant updatedAt;

    @Builder
    public ProductStock(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.updatedAt = Instant.now();
    }

    public void changeQuantity(int quantity) {
        this.quantity = quantity;
        this.updatedAt = Instant.now();
    }
}
