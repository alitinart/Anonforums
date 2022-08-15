package com.anon.anonforums.services;

import com.anon.anonforums.model.User;
import com.anon.anonforums.repository.UserRepo;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
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

    public User findUserByAddress(String address) {
        List<User> users = this.userRepo.findAll();

        Optional<User> optionalUser = users.stream().filter((anon) -> verifyAddress(address, anon.getAddress())).findFirst();

        if(!optionalUser.isPresent()) {
            throw new NoSuchElementException("No user found");
        }

        User user = optionalUser.get();
        return user;
    }

    public void createUser(String address) {
        List<User> allUsers = this.userRepo.findAll();

//        HASH ADDRESS
        String hashedAddress = BCrypt.hashpw(address, BCrypt.gensalt());

//        Check for available name, check if there is already a user with that ip and create user if all fulfilled
        if(allUsers.size() == 0) {
            User newUser = new User("Anonymous 0", hashedAddress);
            this.userRepo.save(newUser);
            return;
        }

        allUsers.forEach((anon) -> {
            String[] splitName = anon.getName().split(" ");
            int anonNumber = Integer.parseInt(splitName[1]);

            int nextAnonNumber = anonNumber + 1;
            User nextAnon = this.userRepo.findUserByName("Anonymous " + nextAnonNumber);

            if(verifyAddress(address, anon.getAddress())) {
                throw new IllegalArgumentException("Already a user with this address");
            }

            try {
                nextAnon.getId();
            } catch (Exception e) {
                User newUser = new User("Anonymous " + nextAnonNumber, hashedAddress);
                this.userRepo.save(newUser);
            }
        });
    }

    public boolean verifyAddress(String unHashedAddress, String hashedAddress) {
        if(BCrypt.checkpw(unHashedAddress,hashedAddress)) {
            return true;
        }

        return false;
    }

    public String getIpAddress(HttpServletRequest request) {
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

    public void deleteUser(String id, String secretToken) {
        Dotenv dotenv = Dotenv.load();

        if(!Objects.equals(secretToken, dotenv.get("SECRET_TOKEN"))) {
            throw new IllegalArgumentException("The Secret token provided is not correct");
        }

        User user = this.userRepo.findUserById(id);

        try {
            user.getId();
        } catch (Exception e) {
            throw new IllegalArgumentException("No user with that ID");
        }

        this.userRepo.delete(user);
    }
}
