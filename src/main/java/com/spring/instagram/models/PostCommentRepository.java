package com.spring.instagram.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
    @Query(nativeQuery = true, value = """
            INSERT INTO post_comment(body, comment_time, comment_by_account_id, post_id)
            VALUE (:body, NOW(), SELECT account_id FROM account where email = :email, :postid)
            """)
    void createComment(@Param("body")String body, @Param("email") String email, @Param("postid") int postid);

    @Query(nativeQuery = true, value = """
            SELECT body,comment_time, email from post_comment pc INNER JOIN account a on pc.comment_by_account_id = a.account_id
            where post_id = :postid
            order by comment_time desc, post_comment_id desc
            LIMIT 10 OFFSET :offset
            """)
    List<Object[]> getCommentList(@Param("postid") int postid, @Param("offset") int offset);
}
