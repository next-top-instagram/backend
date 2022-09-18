package com.spring.instagram.create;

import com.spring.instagram.models.Post;
import com.spring.instagram.models.PostItemModel;
import com.spring.instagram.models.PostItemRepository;
import com.spring.instagram.models.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/post")
public class PostService {
    private PostRepository postRepository;
    private PostItemRepository postItemRepository;

    @Autowired
    public PostService(PostRepository postRepository, PostItemRepository postItemRepository){
        this.postRepository=postRepository;
        this.postItemRepository = postItemRepository;
    }
    @GetMapping
    public List<Post> postList() {
        return this.postRepository.findAll();
    }

//    SELECT p.*,
//    a.email,
//    r.url
//    FROM   `post` p
//    INNER JOIN account a
//    ON p.write_by = a.account_id
//    INNER JOIN resource r
//    ON p.image_id = r.resource_id
//    WHERE  a.email = 'user1@example.com';
    @GetMapping(path = "{email}")
    public List<PostItemModel> userPostList(@PathVariable String email) {
        return this.postRepository.findPostListByUser(email);
    }

    @PostMapping
    public String createPost(@RequestBody Post post){
        try{
            this.postRepository.save(post);
            return "create Post";
        }
        catch (Exception e){
            return "Fail";
        }

    }
    @PutMapping
    public String updatePost(@RequestBody Post post) {
        try {
            Optional<Post> postOptional = this.postRepository.findById(post.getId());
            if (postOptional.isPresent()) {
                Post updatePost = postOptional.get();
                updatePost.setBody(post.getBody());
//                updatePost.setWrite_by(post.getWrite_by());
//                updatePost.setImage_id(post.getImage_id());
//                updatePost.setGood_cnt(post.getGood_cnt());
//                updatePost.setCreate_time(post.getCreate_time());
                this.postRepository.save(updatePost);
            } else {
                return "update Fail";
            }
        } catch (Exception err) {
            return "Fail";
        }
        return "update OK";
    }
    @DeleteMapping(path="{id}")
    public String deletePost(@PathVariable Long id){
        try {
            Optional<Post> postOptional = this.postRepository.findById(id);
            if (postOptional.isPresent()) {
                this.postRepository.delete(postOptional.get());
            } else {
                return "delete Fail";
            }
        } catch(Exception err) {
            return "Fail";
        }
        return "delete OK";

    }


}
