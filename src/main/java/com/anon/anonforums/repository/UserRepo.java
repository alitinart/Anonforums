package com.anon.anonforums.repository;

import com.anon.anonforums.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepo extends MongoRepository<User, String> {

    @Query("{address:'?0'}")
    User findUserByAddress(String address);

    @Query("{name:'?0'}")
    User findUserByName(String name);
}
