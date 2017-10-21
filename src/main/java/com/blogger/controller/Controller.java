package com.blogger.controller;

import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogger.domain.BlogPost;
import com.blogger.domain.Comment;
import com.blogger.repository.BlogPostRepository;
import com.blogger.repository.CommentRepository;

@RestController
@RequestMapping("/blogger/1.0")
public class Controller {

  private BlogPostRepository blogPostRepository;
  private CommentRepository commentRepository;

  public Controller(BlogPostRepository blogPostRepository, CommentRepository commentRepository)
      throws UnknownHostException {
    this.blogPostRepository = blogPostRepository;
    this.commentRepository = commentRepository;
  }

  @PostMapping("/posts")
  public ResponseEntity<BlogPost> addNewPost(@RequestBody BlogPost post) {
    blogPostRepository.save(post);
    return new ResponseEntity<>(post, HttpStatus.OK);
  }

  @GetMapping("/posts")
  public List<BlogPost> getAllPosts() {
    return blogPostRepository.findAllByOrderByCreatedDateDesc();
  }

  @DeleteMapping("/posts")
  public void deleteAllPosts() {
    blogPostRepository.deleteAll();
  }

  @DeleteMapping("/posts/{id}")
  public List<BlogPost> deletePost(@PathVariable String id) {
    blogPostRepository.delete(id);
    return blogPostRepository.findAllByOrderByCreatedDateDesc();
  }

  @PostMapping("/comments")
  public ResponseEntity<Comment> addNewComment(@RequestBody Comment comment) {
    commentRepository.save(comment);
    return new ResponseEntity<>(comment, HttpStatus.OK);
  }

  @GetMapping("/comments")
  public List<Comment> getAllCommentsForPost(@RequestParam("id") String id) {
    return commentRepository.findByPostIdOrderByCommentDateDesc(id);
  }

  @DeleteMapping("/comments/{id}")
  public ResponseEntity<String> deleteComment(@PathVariable String id) {
    commentRepository.delete(id);
    return new ResponseEntity<>(null, HttpStatus.OK);
  }

  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return "redirect:/login?logout";
  }

}
