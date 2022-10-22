package com.spring.instagram.create;

import com.spring.instagram.login.SessionCheck;
import com.spring.instagram.models.BasicResponseModel;
import com.spring.instagram.models.Post;
import com.spring.instagram.models.PostItemModel;
//import com.spring.instagram.models.PostItemRepository;
import com.spring.instagram.models.PostRepository;
import com.spring.instagram.resource.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/post")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostService {
    private PostRepository postRepository;
//    private PostItemRepository postItemRepository;

    private final int PAGE_LIMIT_SIZE = 3;
    private final int MY_PROFILE_PAGE_LIMIT_SIZE = 9;
    private final StorageService storageService;
    @Autowired
    public PostService(PostRepository postRepository, StorageService storageService) {
        this.postRepository=postRepository;
        this.storageService = storageService;
    }
    @GetMapping
    public List<PostItemModel> postList(@RequestParam(required = false, defaultValue = "0") int page) {
        List<PostItemModel> postList = this.postRepository.getLatestPostList(page * PAGE_LIMIT_SIZE).stream().map(post -> new PostItemModel(
                ((BigInteger) post[0]).longValue(),
                (String) post[1],
                (Date) post[2],
                (Integer) post[3],
                (String) post[4],
                (String) post[5])).collect(Collectors.toList());
        return postList;
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
    public List<PostItemModel> userPostList(@PathVariable String email, @RequestParam(required = false, defaultValue = "0") int page) {
        List<PostItemModel> postList = this.postRepository.findPostListByUser(email, page * MY_PROFILE_PAGE_LIMIT_SIZE).stream().map(post -> new PostItemModel(
                ((BigInteger) post[0]).longValue(),
                (String) post[1],
                (Date) post[2],
                (Integer) post[3],
                (String) post[4],
                (String) post[5])).collect(Collectors.toList());
        return postList;
    }

    @PostMapping
    @SessionCheck

    public String createPost(String userName, @RequestPart("body") String body, @RequestPart("file") MultipartFile file){

        try{
//            this.postRepository.save(post);
//            this.postRepository.save(new Post(asdfasf));
            long fileId = storageService.store(file);
            System.out.println("store id: " + fileId);
            // TODO
            // 파일 저장 후 db 에 등록
            // 사용자 idx 값 불러오기, 여의치 않으면 걍 하드코딩
            // 사용자 입력한 게시물 내용 insert
//            this.postRepository.save(new Post(postCreateModel.getBody(), 1, fileId));
            int result = this.postRepository.insertNewPost(body, 1L, fileId);
            System.out.println("result: " + result);
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
    @SessionCheck
    public BasicResponseModel deletePost(String userName, @PathVariable Long id){
        try {
            // TODO
            // 삭제요청한 사용자의 아이디가 작성자일 경우 @SessionChecker
            // 해당 게시물이 실제로 있는 경우
            Optional<Post> postOptional = this.postRepository.findById(id);
            if (postOptional.isPresent()
                    && postOptional.get().getWriteBy().getEmail().equals(userName)) {

                this.postRepository.delete(postOptional.get());

                return new BasicResponseModel(true, "OK", null);
            } else {
                return new BasicResponseModel(false, "Not exist or permission denied", null);
            }
        } catch(Exception err) {
            return new BasicResponseModel(false, err.getMessage(), null);
        }
    }


}
