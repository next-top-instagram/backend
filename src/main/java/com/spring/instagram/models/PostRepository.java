package com.spring.instagram.models;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
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
    List<Object[]> findPostListByUser(@Param("email") String email);

    @Modifying
    @Query(nativeQuery = true,
            value = "INSERT INTO post (body, write_by, image_id, good_cnt, create_time) VALUES (:body, :writer, :resourceId, 0, NOW())"
    )
    @Transactional
    int insertNewPost(@Param("body") String body, @Param("writer") Long writer, @Param("resourceId") Long resourceId);
}
