package com.blogger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogger.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
  
  List<Comment> findByPostIdOrderByCommentDateDesc(String id);

}
