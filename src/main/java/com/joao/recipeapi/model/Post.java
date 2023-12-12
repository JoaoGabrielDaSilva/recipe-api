package com.joao.recipeapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity()
@Getter()
@Setter()
@Table(name = "post")
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String description;

    private Integer likeCount;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;


}
