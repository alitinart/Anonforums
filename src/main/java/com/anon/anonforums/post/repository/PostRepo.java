package com.anon.anonforums.post.repository;

import com.anon.anonforums.post.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends MongoRepository<Post, String> {

    @Query("{id:'?0'}")
    Post findPostById(String id);

}
