package com.spring.instagram.models;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
//    @Query(value = "Select p from Post p Inner Join PostComment pc Inner Join PostGood pg")
//    List<Object> findPost(Pageable pageable);
    @Query(nativeQuery = true, value = """
            SELECT p.post_id,
                p.body,
                p.create_time,
                p.good_cnt,
                a.email,
                r.url
                FROM   post p
                INNER JOIN account a
                ON p.write_by = a.account_id
                INNER JOIN resource r
                ON p.image_id = r.resource_id
                WHERE  a.email = :email
            """)
    List<PostItemModel> findPostListByUser(@Param("email") String email);

}
