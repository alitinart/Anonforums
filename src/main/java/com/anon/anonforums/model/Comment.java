package com.anon.anonforums.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Comment {

    private String content;
    private List<Comment> replies;
    private String author;
}
