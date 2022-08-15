package com.anon.anonforums.controller;

import com.anon.anonforums.model.Post;
import com.anon.anonforums.services.PostService;
import com.anon.anonforums.model.Response;
import com.anon.anonforums.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/posts")
@CrossOrigin()
public class PostController {

    public final PostService postService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
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

    @DeleteMapping(path="/{id}")
    public Response deletePost(@PathVariable("id") String id, @RequestHeader(value = "Secret-Token") String secretToken) {
        this.postService.deletePostById(id, secretToken);
        return new Response(false, "Post Deleted", 200);
    }

}
