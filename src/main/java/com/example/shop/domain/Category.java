package com.example.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "categories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Category parent;

    @Column(name="sort_order", nullable = false)
    private int sortOrder;

    @Builder
    public Category(String name, Category parent, int sortOrder) {
        this.name = name;
        this.parent = parent;
        this.sortOrder = sortOrder;
    }

    public void changeName(String name) { this.name = name; }
    public void changeParent(Category parent) { this.parent = parent; }
    public void changeSortOrder(int sortOrder) { this.sortOrder = sortOrder; }
}
