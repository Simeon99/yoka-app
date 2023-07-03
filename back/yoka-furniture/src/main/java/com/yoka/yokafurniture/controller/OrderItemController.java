package com.yoka.yokafurniture.controller;

import com.yoka.yokafurniture.payload.OrderItemDto;
import com.yoka.yokafurniture.service.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {

    private OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("/article/{articleId}/quantity/{quantity}")
    public ResponseEntity<OrderItemDto> createOrderItem(@PathVariable long articleId, @PathVariable int quantity){

        return new ResponseEntity<>(orderItemService.createOrederItem(articleId,quantity), HttpStatus.CREATED);

    }
}
