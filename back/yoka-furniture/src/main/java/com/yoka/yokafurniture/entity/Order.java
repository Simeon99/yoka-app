package com.yoka.yokafurniture.entity;

import com.yoka.yokafurniture.exception.AppAPIExceptions;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(updatable = false)
    private Date date;
    private double totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<OrderItem> orderItems = new HashSet<>();
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private DeliveryAddress deliveryAddress;
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private DeliveryContact deliveryContact;


    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void deleteOrderItem(OrderItem orderItem){
        OrderItem orderItemRemove= this.orderItems.stream().filter(o -> o.getId() == orderItem.getId()).findFirst().orElse(null);
        if (orderItemRemove != null){
            this.orderItems.remove(orderItemRemove);
//            orderItemRemove.getArticle().getOrderItems().remove(orderItemRemove);
            orderItemRemove.setOrder(null);
//            orderItemRemove.getArticle().deleteOrderItem(orderItem);
        } else throw  new AppAPIExceptions(HttpStatus.BAD_REQUEST,"Order does not have this orderItem.");
    }

}
