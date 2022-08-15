package com.anon.anonforums.services;

import com.anon.anonforums.model.Post;
import com.anon.anonforums.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PostService {

    private final PostRepo postRepo;

    @Autowired
    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public Post createPost(Post post) {
        this.postRepo.save(post);
        return post;
    }

    public List<Post> findAllPosts() {
        return this.postRepo.findAll();
    }

    public Post findPostById(String id) {
        Post post = this.postRepo.findPostById(id);

        try {
            post.getId();
        } catch (Exception e) {
            throw new IllegalArgumentException("No Post found with that id");
        }

        return post;
    }

    public void deletePostById(String id, String secretToken) {
        if(!Objects.equals(secretToken, System.getenv("SECRET_TOKEN"))) {
            throw new IllegalArgumentException("The Secret token provided is not correct");
        }

        Post post = findPostById(id);
        this.postRepo.delete(post);
    }
}
