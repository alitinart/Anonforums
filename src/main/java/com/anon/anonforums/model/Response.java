package com.anon.anonforums.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {

    private boolean error;
    private String message;
    private int status;

}
