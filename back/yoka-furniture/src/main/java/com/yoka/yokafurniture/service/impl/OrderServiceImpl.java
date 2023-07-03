package com.yoka.yokafurniture.service.impl;

import com.yoka.yokafurniture.entity.*;
import com.yoka.yokafurniture.exception.ResourceNotFoundException;
import com.yoka.yokafurniture.payload.*;
import com.yoka.yokafurniture.repository.ArticleRepository;
import com.yoka.yokafurniture.repository.OrderItemRepository;
import com.yoka.yokafurniture.repository.OrderRepository;
import com.yoka.yokafurniture.service.DeliveryAddressService;
import com.yoka.yokafurniture.service.OrderItemService;
import com.yoka.yokafurniture.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private OrderItemService orderItemService;
    private ArticleRepository articleRepository;
    private DeliveryAddressService deliveryAddressService;
    private ModelMapper mapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, OrderItemService orderItemService, ArticleRepository articleRepository, DeliveryAddressService deliveryAddressService, ModelMapper mapper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderItemService = orderItemService;
        this.articleRepository = articleRepository;
        this.deliveryAddressService = deliveryAddressService;
        this.mapper = mapper;
    }

    @Override
    public OrderDto createOrder(Map<Long, Integer> articlesMap, DeliveryAddressDto deliveryAddressDto) {

        Date curentDate = new Date();

        DeliveryAddress deliveryAddress =  mapToDeliveryAddress(deliveryAddressDto);

        Order order = new Order();

        order.setDeliveryAddress(deliveryAddress);
        deliveryAddress.setOrder(order);

        order.setDate(curentDate);


        for( long key : articlesMap.keySet()){
            OrderItemDto orderItemDto = orderItemService.createOrederItem(key, articlesMap.get(key));
            OrderItem orderItem = orderItemRepository.findById(orderItemDto.getId()).orElseThrow(()-> new ResourceNotFoundException("Order","id",orderItemDto.getId()));
            order.addOrderItem(orderItem);
        }

        double totalPrice = order.getOrderItems()
                .stream()
                .mapToDouble(OrderItem::getPrice)
                .sum();
        order.setTotalPrice(totalPrice);

        Order orderResponse = orderRepository.save(order);



        return mapOrderToDto(orderResponse);
    }

    @Override
    public void removeOrderItem(long orderId, long orderItemId) {

        Order order = orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order", "id", orderId));

        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(()->new ResourceNotFoundException("OrderItem", "id", orderItemId));

        order.deleteOrderItem(orderItem);

        orderRepository.save(order);

    }

    @Override
    public OrderResponse getAllOrders(int pageNo, int pageSize,String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);

        Page<Order> orders= orderRepository.findAll(pageable);

        List<Order> orderList = orders.getContent();

        List<OrderDto> ordersDto = orderList.stream().map(order -> mapOrderToDto(order)).collect(Collectors.toList());

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderDtos(ordersDto);
        orderResponse.setPageNo(orders.getNumber());
        orderResponse.setPageSize(orders.getSize());
        orderResponse.setTotalElements(orders.getTotalElements());
        orderResponse.setTotalPages(orders.getTotalPages());
        orderResponse.setLast(orders.isLast());


        return orderResponse;
    }

    private OrderDto mapOrderToDto(Order order){
//        OrderDto orderDto = mapper.map(order, OrderDto.class);

        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setDate(order.getDate());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setOrderItems(order.getOrderItems().stream().map( orderItem -> mapOrderItemToDto(orderItem)).collect(Collectors.toSet()));
        orderDto.setDeliveryAddressDto(mapToDeliveryAddressDto(order.getDeliveryAddress()));

        return orderDto;
    }

    private OrderItemDto mapOrderItemToDto(OrderItem orderItem){
//        OrderItemDto orderItemDto = mapper.map(orderItem, OrderItemDto.class);

        OrderItemDto orderItemDto = new OrderItemDto();

        orderItemDto.setId(orderItem.getId());
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setArticleDto(articleToDto(orderItem.getArticle()));

        return orderItemDto;

    }
    private ArticleDto articleToDto(Article article) {
        ArticleDto articleDto = mapper.map(article, ArticleDto.class);
        return articleDto;
    }

    private DeliveryAddress mapToDeliveryAddress(DeliveryAddressDto deliveryAddressDto){
        DeliveryAddress deliveryAddress = mapper.map(deliveryAddressDto, DeliveryAddress.class);
        return deliveryAddress;
    }

    private DeliveryAddressDto mapToDeliveryAddressDto(DeliveryAddress deliveryAddress){
        DeliveryAddressDto deliveryAddressDto = mapper.map(deliveryAddress, DeliveryAddressDto.class);
        return deliveryAddressDto;
    }


}
