package com.example.shop.domain;

import com.example.shop.domain.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, length = 200)
    private String name;

    @Column(name="description", columnDefinition = "text")
    private String description;

    @Column(name="price", nullable = false)
    private long price;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false, length = 20)
    private ProductStatus status;

    @Column(name="thumbnail_url", length = 500)
    private String thumbnailUrl;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="category_id")
    )
    private Set<Category> categories = new LinkedHashSet<>();

    @Builder
    public Product(String name, String description, long price, ProductStatus status, String thumbnailUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.thumbnailUrl = thumbnailUrl;
    }

    public void changeBasicInfo(String name, String description, long price, ProductStatus status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
    }

    public void changeThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
