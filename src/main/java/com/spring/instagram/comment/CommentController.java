package com.spring.instagram.comment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/comment")

public class CommentController {

    @GetMapping
    public List<Comment> getComment() {
        return List.of(new Comment(
              1,
                "minsoo ki",
                "집에 가고 싶다"
        ));
    }
}
