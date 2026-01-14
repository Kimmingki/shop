package com.example.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "order_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="product_id", nullable = false)
    private Product product;

    @Column(name="product_name", nullable = false, length = 200)
    private String productName; // 스냅샷

    @Column(name="unit_price", nullable = false)
    private long unitPrice;

    @Column(name="quantity", nullable = false)
    private int quantity;

    @Column(name="line_amount", nullable = false)
    private long lineAmount;

    @Builder
    public OrderItem(Order order, Product product, String productName, long unitPrice, int quantity, long lineAmount) {
        this.order = order;
        this.product = product;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.lineAmount = lineAmount;
    }
}
