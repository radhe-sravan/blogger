package com.blogger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogger.domain.BlogPost;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, String> {

  List<BlogPost> findAllByOrderByCreatedDateDesc();

}
