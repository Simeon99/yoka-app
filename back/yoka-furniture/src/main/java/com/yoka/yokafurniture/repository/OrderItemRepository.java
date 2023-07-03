package com.yoka.yokafurniture.repository;

import com.yoka.yokafurniture.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
