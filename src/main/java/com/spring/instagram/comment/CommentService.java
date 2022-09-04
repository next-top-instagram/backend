package com.spring.instagram.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


import com.spring.instagram.create.Post;
import com.spring.instagram.create.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/comment")

public class CommentService {

    private CommentController commentController;

    @Autowired

    public CommentService(CommentController commentController)
    {
        this.commentController = commentController;
    }

    @GetMapping
    public List<Comment> commentList(){
        return this.commentController.findAll();
    }

    @GetMapping("{Postid}")
    public Comment getComment(@PathVariable Long id) {
        Optional<Comment> commentOptional = this.commentController.findById(id);
        if (commentOptional.isPresent()) {
            return commentOptional.get();
        }


        return null;
    }

}