package com.anon.anonforums.services;

import com.anon.anonforums.model.Post;
import com.anon.anonforums.model.User;
import com.anon.anonforums.repository.PostRepo;
import com.anon.anonforums.repository.UserRepo;
import io.github.cdimascio.dotenv.DotEnvException;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PostService {

    private final PostRepo postRepo;

    @Autowired
    public PostService(PostRepo postRepo, UserRepo userRepo) {
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
        Dotenv dotenv = Dotenv.load();
        if(!Objects.equals(secretToken, dotenv.get("SECRET_TOKEN"))) {
            throw new IllegalArgumentException("The Secret token provided is not correct");
        }

        Post post = findPostById(id);

        try {
            post.getId();
        } catch (Exception e) {
            throw new IllegalArgumentException("No user with that ID");
        }

        this.postRepo.delete(post);
    }
}
