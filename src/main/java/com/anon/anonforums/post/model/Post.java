package com.anon.anonforums.post.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@Validated @Data @Document("posts") @AllArgsConstructor
public class Post {

    @Id
    private String id;

    private String title;
    private String description;
    private String category;
    private String autor;
}
