package com.example.shop.domain;

import com.example.shop.domain.enums.PaymentMethod;
import com.example.shop.domain.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@Entity
@Table(name = "payments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="order_id", nullable = false, unique = true)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(name="method", length = 20)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    @Column(name="status", length = 20)
    private PaymentStatus status;

    @Column(name="pg_tx_id", length = 100)
    private String pgTxId;

    @Column(name="paid_at")
    private Instant paidAt;

    @Column(name="created_at", nullable = false)
    private Instant createdAt;

    @Column(name="updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    void prePersist() {
        var now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    void preUpdate() {
        this.updatedAt = Instant.now();
    }

    @Builder
    public Payment(Order order, PaymentMethod method, PaymentStatus status, String pgTxId, Instant paidAt) {
        this.order = order;
        this.method = method;
        this.status = status;
        this.pgTxId = pgTxId;
        this.paidAt = paidAt;
    }

    public void markPaid(String pgTxId) {
        this.pgTxId = pgTxId;
        this.status = PaymentStatus.SUCCESS;
        this.paidAt = Instant.now();
    }
}
