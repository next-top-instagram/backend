package com.spring.instagram.models;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CustomPostRepositoryHandler implements CustomPostRespository{
    @Autowired
    private EntityManager entityManager;
    @Override
    public List<Object> findPostListByUser(String email) {
        System.out.println("asdfaf");
        return entityManager.createQuery("""
                            SELECT p.id,
                                p.body,
                                p.createTime,
                                p.goodCnt,
                                a.email,
                                r.url
                                FROM   post p
                                INNER JOIN account a
                                ON p.write_by = a.account_id
                                INNER JOIN resource r
                                ON p.image_id = r.resource_id
                                WHERE  a.email = '""" + email + "'", Object.class).getResultList();
    }
}
