package com.yoka.yokafurniture.controller;

import com.yoka.yokafurniture.payload.OrderCreateDto;
import com.yoka.yokafurniture.payload.OrderDto;
import com.yoka.yokafurniture.payload.OrderResponse;
import com.yoka.yokafurniture.service.OrderService;
import com.yoka.yokafurniture.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderCreateDto orderCreateDto){

        return new ResponseEntity<>(orderService.createOrder(orderCreateDto.getArticlesMap(), orderCreateDto.getDeliveryAddressDto()), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<OrderResponse> getAllOrders(@RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NO,required = false) int pageNo,
                                                      @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                                      @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY,required = false)String sortBy,
                                                      @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false)String sortDir){

        return new ResponseEntity<>(orderService.getAllOrders(pageNo, pageSize, sortBy, sortDir), HttpStatus.CREATED);

    }


    @DeleteMapping("/{orderId}/orderItem/{orderItemId}")
    public ResponseEntity<String> removeOrderItem(@PathVariable long orderId, @PathVariable long orderItemId){
        orderService.removeOrderItem(orderId,orderItemId);
        return new ResponseEntity<>("OrderDeleted", HttpStatus.OK);

    }

}
