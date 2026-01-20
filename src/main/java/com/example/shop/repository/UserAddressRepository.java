package com.example.shop.repository;

import com.example.shop.domain.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

    List<UserAddress> findAllByUserId(Long userId);
    Optional<UserAddress> findByUserIdAndDefaultTrue(Long userId);
}
