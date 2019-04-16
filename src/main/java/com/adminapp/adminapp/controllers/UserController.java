package com.adminapp.adminapp.controllers;

import com.adminapp.adminapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserRepository repo;

    @GetMapping(value = "/login")
    public String index() {
        return "login";
    }

    @Autowired
    private TokenStore store;

    @GetMapping(value = "admin/logouts")
    public void logout(@RequestParam (value = "access_token") String accessToken) {
        store.removeAccessToken(store.readAccessToken(accessToken));
    }

    @GetMapping(value = "/getUsername")
    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
