package com.example.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user_addresses")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAddress extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(name="label", length = 30)
    private String label;

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

    @Column(name="is_default", nullable = false)
    private boolean isDefault;

    @Builder
    public UserAddress(User user, String label, String receiverName, String receiverPhone,
                       String zipCode, String address1, String address2, boolean isDefault) {
        this.user = user;
        this.label = label;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.zipCode = zipCode;
        this.address1 = address1;
        this.address2 = address2;
        this.isDefault = isDefault;
    }

    public void setDefault(boolean value) {
        this.isDefault = value;
    }
}
