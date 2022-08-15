package com.anon.anonforums.post.controller;

import com.anon.anonforums.post.model.Post;
import com.anon.anonforums.post.services.PostService;
import com.anon.anonforums.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/posts")
@CrossOrigin()
public class PostController {

    public final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping
    public Response createPost(@RequestBody Post post) {
        this.postService.createPost(post);
        return new Response(false, "Post created", 200);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return this.postService.findAllPosts();
    }

    @GetMapping(path="/{id}")
    public Post getPostById(@PathVariable("id") String id) {
        return this.postService.findPostById(id);
    }
}
