package com.yoka.yokafurniture.service.impl;

import com.yoka.yokafurniture.entity.Article;
import com.yoka.yokafurniture.entity.ArticlePrice;
import com.yoka.yokafurniture.entity.Order;
import com.yoka.yokafurniture.entity.OrderItem;
import com.yoka.yokafurniture.exception.ResourceNotFoundException;
import com.yoka.yokafurniture.payload.Article.ArticleDto;
import com.yoka.yokafurniture.payload.Order.OrderDto;
import com.yoka.yokafurniture.payload.OrderItem.OrderItemDto;
import com.yoka.yokafurniture.repository.ArticlePriceRepository;
import com.yoka.yokafurniture.repository.ArticleRepository;
import com.yoka.yokafurniture.repository.OrderItemRepository;
import com.yoka.yokafurniture.repository.OrderRepository;
import com.yoka.yokafurniture.service.OrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderItemImpl implements OrderItemService {


    private ArticleRepository articleRepository;
    private OrderItemRepository orderItemRepository;
    private ArticlePriceRepository articlePriceRepository;
    private OrderRepository orderRepository;
    private ModelMapper mapper;

    public OrderItemImpl(ArticleRepository articleRepository, OrderItemRepository orderItemRepository, ArticlePriceRepository articlePriceRepository, OrderRepository orderRepository, ModelMapper mapper) {
        this.articleRepository = articleRepository;
        this.orderItemRepository = orderItemRepository;
        this.articlePriceRepository = articlePriceRepository;
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    @Override
    public OrderItemDto createOrederItem(long articleId, int quantity, double width, double length, double height) {


        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ResourceNotFoundException("Article", "id", articleId));

        ArticlePrice articlePrice = articlePriceRepository.findByArticleIdAndDimensions(articleId,width, length, height);


        OrderItem orderItem = new OrderItem();
        orderItem.setArticle(article);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(articlePrice);
        article.getOrderItems().add(orderItem);

        OrderItem orderItemResponse = orderItemRepository.save(orderItem);

        OrderItemDto orderItemDto = new OrderItemDto();

        orderItemDto.setId(orderItemResponse.getId());
        orderItemDto.setArticleDto(mapArticleToDto(article));
        orderItemDto.setQuantity(orderItemResponse.getQuantity());
        orderItemDto.setPrice(orderItemResponse.getPrice());

        return orderItemDto;
    }

    private OrderItemDto mapToDto(OrderItem orderItem) {


        OrderItemDto orderItemDto = mapper.map(orderItem, OrderItemDto.class);

        return orderItemDto;


    }

    private ArticleDto mapArticleToDto(Article article){
        ArticleDto articleDto = mapper.map(article, ArticleDto.class);

        return articleDto;
    }

    private OrderDto mapOrderToDto(Order order){
        OrderDto orderDto = mapper.map(order, OrderDto.class);

        return orderDto;
    }

}
