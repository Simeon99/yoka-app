package com.yoka.yokafurniture.entity;

import com.yoka.yokafurniture.exception.AppAPIExceptions;
import com.yoka.yokafurniture.payload.Dimension.DimensionType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "dimensions")
public class Dimension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "dimension", nullable = false)
    private double dimension;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private DimensionType type;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "dimensions")
    private Set<Article> articles = new HashSet<>();


    public void addArticles(Article article){
        this.articles.add(article);
        article.getDimensions().add(this);
    }

    public void deleteArticle(Article article){
        Article articleRemove= this.articles.stream().filter(a -> a.getId() == article.getId()).findFirst().orElse(null);
        if (articleRemove != null){
            this.articles.remove(articleRemove);
            articleRemove.getDimensions().remove(this);
        } else throw  new AppAPIExceptions(HttpStatus.BAD_REQUEST,"Article does not have this dimension.");
    }

}
