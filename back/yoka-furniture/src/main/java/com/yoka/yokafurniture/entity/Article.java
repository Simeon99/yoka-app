package com.yoka.yokafurniture.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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
    @Column(name = "body_inside")
    private String bodyInside;
    @Column(name = "body_outside")
    private String bodyOutside;
    @Column(name = "bass_and_legs")
    private String baseAndLegs;
    @Column(name = "system_mechanism")
    private String systemMechanism;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "discount")
    private double discount;

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

    private void addColour(Colour colour){
        this.colours.add(colour);
        colour.getArticles().add(this);
    }

}
