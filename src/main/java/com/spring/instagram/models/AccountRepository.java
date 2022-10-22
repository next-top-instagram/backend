package com.spring.instagram.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "update Account set `password` = :password where email = :email", nativeQuery = true)
    void updateAccountPassword(String email, String password);

    @Query(value = "select count(*) from Account a where a.email = ?1", nativeQuery = true)
    Long isAccountAlreadyExist(String email);

    @Query(value = "select count(*) from Account a where a.email = ?1 and a.password = ?2", nativeQuery = true)
    Long authenticationViaEmailAndPassword(String email, String password);
}
