package com.yoka.yokafurniture.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yoka.yokafurniture.exception.AppAPIExceptions;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Article{
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "name_sr", nullable = false)
    private String nameSr;
    @Column(name = "discount")
    private double discount;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Description> descriptions = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "article_details",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "colour_id")
    )
    private Set<Colour> colours = new HashSet<>();;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "article_dimensions",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "dimension_id")
    )
    private Set<Dimension> dimensions = new HashSet<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ArticleImage> articleImages = new HashSet<>();


    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<OrderItem> orderItems = new HashSet<>();
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ArticlePrice> articlePrice = new HashSet<>();

    private void addColour(Colour colour){
        this.colours.add(colour);
        colour.getArticles().add(this);
    }

    public void deleteOrderItem(OrderItem orderItem){
        OrderItem orderItemRemove= this.orderItems.stream().filter(o -> o.getId() == orderItem.getId()).findFirst().orElse(null);
        if (orderItemRemove != null){
            this.orderItems.remove(orderItemRemove);
        } else throw  new AppAPIExceptions(HttpStatus.BAD_REQUEST,"Order does not have this orderItem.");
    }


}
