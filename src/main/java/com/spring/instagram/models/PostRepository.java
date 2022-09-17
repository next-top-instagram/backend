package com.spring.instagram.models;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
//    @Query(value = "Select p from Post p Inner Join PostComment pc Inner Join PostGood pg")
//    List<Object> findPost(Pageable pageable);
}
