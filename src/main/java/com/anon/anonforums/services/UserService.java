package com.anon.anonforums.services;

import com.anon.anonforums.model.User;
import com.anon.anonforums.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User findUserByAddress(String address) {
        User user = this.userRepo.findUserByAddress(address);

        try {
            user.getId();
        } catch (Exception e) {
            throw new IllegalArgumentException("No User found with that name");
        }

        return user;
    }

    public User findUserByName(String name) {

        User user = this.userRepo.findUserByName(name);

        try {
            user.getId();
        } catch (Exception e) {
            throw new IllegalArgumentException("No User found with that name");
        }

        return user;
    }

    public List<User> findAllUsers() {
        return this.userRepo.findAll();
    }

    public void createUser(String address) {
        List<User> allUsers = this.userRepo.findAll();
        String avaliableName = "";
        allUsers.forEach((anon) -> {
            String[] splitName = anon.getName().split(" ");
            int anonNumber = Integer.parseInt(splitName[1]);

            int nextAnonNumber = anonNumber + 1;
            User nextAnon = this.userRepo.findUserByName("Anonymous " + nextAnonNumber);

            try {
                nextAnon.getId();
            } catch (Exception e) {
                User newUser = new User("Anonymous " + nextAnonNumber, address);
                this.userRepo.save(newUser);
            }
        });
    }
}
