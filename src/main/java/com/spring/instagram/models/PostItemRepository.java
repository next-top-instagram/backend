package com.spring.instagram.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@NoRepositoryBean
public interface PostItemRepository extends JpaRepository<PostItemModel, Long>, CustomPostRespository<PostItemModel> {
//    List<PostItemModel> findPostListByUser(@Param("email") String email);
}
