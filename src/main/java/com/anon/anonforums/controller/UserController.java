package com.anon.anonforums.controller;

import com.anon.anonforums.model.Response;
import com.anon.anonforums.model.User;
import com.anon.anonforums.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User getUserByAddress(@RequestParam("address") String address, @RequestParam("name") String name) {
        if(name.isEmpty()) {
            return this.userService.findUserByAddress(address);
        }
        return this.userService.findUserByName(name);
    }

    @GetMapping(path="/add")
    public String createUser(HttpServletRequest request) {
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
}
