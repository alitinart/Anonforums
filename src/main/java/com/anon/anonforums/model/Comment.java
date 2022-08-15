package com.anon.anonforums.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
public class Comment {

    @Id
    private String id;

    @NotNull
    private String content;

    private List<Comment> replies;

    @NotNull
    private String author;

}
