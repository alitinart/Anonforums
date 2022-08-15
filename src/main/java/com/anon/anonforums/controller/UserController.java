package com.anon.anonforums.controller;

import com.anon.anonforums.model.Response;
import com.anon.anonforums.model.User;
import com.anon.anonforums.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path="/find")
    public User findUserByName(@RequestParam("name") String name) {
        return this.userService.findUserByName(name);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return this.userService.findAllUsers();
    }

    @GetMapping(path="/add")
    public Response createUser(HttpServletRequest request) {

        String remoteAddr = this.userService.getIpAddress(request);
        this.userService.createUser(remoteAddr);

        return new Response(false, "User Created", 200);
    }

    @DeleteMapping(path="/{id}")
        public Response deletePost(@PathVariable("id") String id, @RequestHeader(value = "Secret-Token") String secretToken) {
        System.out.println(System.getenv("SECRET_TOKEN"));
        this.userService.deleteUser(id, secretToken);
        return new Response(false, "User Deleted", 200);
    }
}
