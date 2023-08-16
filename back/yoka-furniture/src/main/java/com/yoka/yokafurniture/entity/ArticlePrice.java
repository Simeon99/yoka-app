package com.yoka.yokafurniture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class ArticlePrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double width;
    private double length;
    private double height;
    private double price;
    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;
}
