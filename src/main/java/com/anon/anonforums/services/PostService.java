package com.anon.anonforums.services;

import com.anon.anonforums.model.Comment;
import com.anon.anonforums.model.Post;
import com.anon.anonforums.model.User;
import com.anon.anonforums.repository.PostRepo;
import com.anon.anonforums.repository.UserRepo;
import io.github.cdimascio.dotenv.DotEnvException;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public void addComment(String id, Comment comment) {
        Post post = this.postRepo.findPostById(id);

        try {
            post.getId();
        } catch (Exception e) {
            throw new NoSuchElementException("No post with that ID");
        }


        if(post.getComments() == null) {
            post.setComments(new ArrayList<>());
        }

        List<Comment> comments = post.getComments();
        comments.add(comment);

        post.setComments(comments);

        this.postRepo.save(post);
    }

//    Not DONE
    public void addReply(int index, String id, Comment reply) {
        Post post = this.postRepo.findPostById(id);

        try {
            post.getId();
        } catch (Exception e) {
            throw new NoSuchElementException("No post with that ID");
        }

        Comment comment = post.getComments().get(index);
        if(comment.getReplies() == null) {
            comment.setReplies(new ArrayList<>());
        }

        this.postRepo.save(post);
    }
}
