package com.anon.anonforums.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated @Data @Document("posts") @AllArgsConstructor
public class Post {

    @Id
    private String id;

    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String author;

    private List<Comment> comments;
}
