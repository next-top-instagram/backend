package com.spring.instagram.create;

import com.spring.instagram.login.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/post")
public class PostService {
    private PostRepository postRepository;

    @Resource
    private UserInfo userInfo;

    @Autowired
    public PostService(PostRepository postRepository,UserInfo userInfo){
        this.postRepository=postRepository;
        this.userInfo=userInfo;
    }
    @GetMapping
    public List<Post> postList() {
        return this.postRepository.findAll();
    }

    @PostMapping
    public String createPost(@RequestBody Post post){
        try{
            if(userInfo.getUserNm()!=null && userInfo.getUserId()==post.getWrite_by().getUserId()) {
                this.postRepository.save(post);
                return "create Post";
            }
            else{
                return "please login";
            }
        }
        catch (Exception e){
            return "fail";
        }

    }
    @PutMapping
    public String updatePost(@RequestBody Post post) {
        try {
            Optional<Post> postOptional = this.postRepository.findById(post.getId());
            if (postOptional.isPresent() &&userInfo.getUserNm()!=null && userInfo.getUserId()==post.getWrite_by().getUserId()) {
                Post updatePost = postOptional.get();
                updatePost.setBody(post.getBody());
                updatePost.setWrite_by(post.getWrite_by());
                updatePost.setImage_id(post.getImage_id());
                updatePost.setGood_cnt(post.getGood_cnt());
                updatePost.setCreate_time(post.getCreate_time());
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
            if (postOptional.isPresent()&& userInfo.getUserNm()!=null && userInfo.getUserId()==postOptional.get().getWrite_by().getUserId()) {
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
