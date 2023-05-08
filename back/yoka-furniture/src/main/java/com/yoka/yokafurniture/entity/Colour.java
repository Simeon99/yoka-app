package com.yoka.yokafurniture.entity;

import com.yoka.yokafurniture.exception.AppAPIExceptions;
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
@Table(name = "colours")
public class Colour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "name_sr", nullable = false)
    private String nameSr;

    private String mediaLink;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "colours")
    private Set<Article> articles = new HashSet<>();;

    public void addArticles(Article article){
        this.articles.add(article);
        article.getColours().add(this);

    }

    public void deleteArticles(Article article){
        Article articleRemove= this.articles.stream().filter(a -> a.getId() == article.getId()).findFirst().orElse(null);
        if (articleRemove != null){
            this.articles.remove(articleRemove);
            articleRemove.getColours().remove(this);
        } else throw  new AppAPIExceptions(HttpStatus.BAD_REQUEST,"Article does not have this colour.");
    }

}
