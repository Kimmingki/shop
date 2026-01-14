package com.example.shop.domain;

import com.example.shop.domain.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="order_no", nullable = false, unique = true, length = 30)
    private String orderNo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false, length = 20)
    private OrderStatus status;

    @Column(name="total_amount", nullable = false)
    private long totalAmount;

    @Column(name="receiver_name", nullable = false, length = 50)
    private String receiverName;

    @Column(name="receiver_phone", nullable = false, length = 20)
    private String receiverPhone;

    @Column(name="zip_code", nullable = false, length = 10)
    private String zipCode;

    @Column(name="address1", nullable = false, length = 200)
    private String address1;

    @Column(name="address2", length = 200)
    private String address2;

    @Column(name="created_at", nullable = false)
    private java.time.Instant createdAt;

    @Column(name="updated_at", nullable = false)
    private java.time.Instant updatedAt;

    @PrePersist
    void prePersist() {
        var now = java.time.Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    void preUpdate() {
        this.updatedAt = java.time.Instant.now();
    }

    @Builder
    public Order(String orderNo, User user, OrderStatus status, long totalAmount,
                 String receiverName, String receiverPhone, String zipCode, String address1, String address2) {
        this.orderNo = orderNo;
        this.user = user;
        this.status = status;
        this.totalAmount = totalAmount;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.zipCode = zipCode;
        this.address1 = address1;
        this.address2 = address2;
    }

    public void changeStatus(OrderStatus status) {
        this.status = status;
    }
}
