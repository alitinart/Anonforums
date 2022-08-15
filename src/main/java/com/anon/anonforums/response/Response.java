package com.anon.anonforums.response;

import com.anon.anonforums.post.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.type.AnyType;

@Data
@AllArgsConstructor
public class Response {

    private boolean error;
    private String message;
    private int status;

}
