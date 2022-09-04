package com.spring.instagram.comment;


import org.springframework.data.jpa.repository.JpaRepository;
public interface CommentController extends JpaRepository<Comment, Long>{

}

