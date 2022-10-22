package com.spring.instagram.comment;

import com.spring.instagram.login.SessionCheck;
import com.spring.instagram.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/comment")

public class CommentController {

    private final int COMMENT_PAGE_LIMIT = 10;

    @Autowired
    private final PostCommentRepository postCommentRepository;

    public CommentController(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }

    @GetMapping(path = "{id}")
    public List<PostCommentResModel> getComment(@PathVariable int id, @RequestParam(required = false, defaultValue = "0") int page) {
        List<PostCommentResModel> commentList = this.postCommentRepository.getCommentList(id, page * COMMENT_PAGE_LIMIT).stream().map(post -> new PostCommentResModel(
                (String) post[2],
                (String) post[0],
                (Date) post[1])).collect(Collectors.toList());
        return commentList;
    }

    @PostMapping
    @SessionCheck
    public BasicResponseModel createComment(String userName, @RequestBody PostCommentModel postCommentModel) {
        try {
            this.postCommentRepository.createComment(postCommentModel.getBody(), userName, postCommentModel.getPostid());
            return new BasicResponseModel(true, "OK", null);
        } catch (Exception e) {
            return new BasicResponseModel(false, e.getMessage(), null);
        }
    }
}
