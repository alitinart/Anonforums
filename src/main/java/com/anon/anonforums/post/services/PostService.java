package com.anon.anonforums.post.services;

import com.anon.anonforums.post.model.Post;
import com.anon.anonforums.post.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
