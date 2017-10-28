package com.blogger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogger.domain.BlogUser;

@Repository
public interface BlogUserRepository extends JpaRepository<BlogUser, String> {

}
