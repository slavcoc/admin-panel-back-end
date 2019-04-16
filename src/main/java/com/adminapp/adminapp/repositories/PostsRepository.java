package com.adminapp.adminapp.repositories;

import com.adminapp.adminapp.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    Posts getPostById(Long id);

    @Override
    Page<Posts>findAll(Pageable pageable);

    Page<Posts>findAllByOrderByDateDesc(Pageable pageable);
}
