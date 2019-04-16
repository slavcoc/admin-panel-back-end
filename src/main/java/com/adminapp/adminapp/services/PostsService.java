package com.adminapp.adminapp.services;

import com.adminapp.adminapp.repositories.PostsRepository;
import com.adminapp.adminapp.entity.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostsService {
    @Autowired
    PostsRepository postsRepository;

    public Page<Posts> findAllPosts(Pageable pageable) {
        return postsRepository.findAllByOrderByDateDesc(pageable);
    }


    public void insertPost(Posts post) {
        postsRepository.save(post);
    }

    public void deleteById(Long id) {
        postsRepository.deleteById(id);
    }

    public Posts findById(Long id) {
        return postsRepository.getPostById(id);
    }


}
