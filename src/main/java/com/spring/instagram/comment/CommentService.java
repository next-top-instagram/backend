/*package com.spring.instagram.comment;


import com.spring.instagram.create.Post;
import com.spring.instagram.create.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/comment")
public class CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository){
        this.commentRepository=commentRepository;

    }

    @PostMapping
    public Optional<Comment> commentList(@RequestBody Comment comment) {
        return this.commentRepository.findById(comment.getPost().getId());

    }


}
*/