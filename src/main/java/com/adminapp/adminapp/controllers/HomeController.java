package com.adminapp.adminapp.controllers;

import com.adminapp.adminapp.configs.EmailServiceImpl;
import com.adminapp.adminapp.entity.Posts;
import com.adminapp.adminapp.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    PostsService postsService;

    @Autowired
    EmailServiceImpl emailService;

    @GetMapping(value = "admin/posts")
    public Page<Posts> privatePage(@PageableDefault(value=15, page=0) Pageable pageable) {
        return postsService.findAllPosts(pageable);
    }

}
