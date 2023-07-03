package com.yoka.yokafurniture.repository;

import com.yoka.yokafurniture.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
