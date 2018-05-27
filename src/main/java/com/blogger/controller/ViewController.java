package com.blogger.controller;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blogger.repository.BlogPostRepository;
import com.blogger.repository.CommentRepository;

@Controller
public class ViewController {

  private String username;

  private BlogPostRepository blogPostRepository;
  private CommentRepository commentRepository;

  public ViewController(BlogPostRepository blogPostRepository,
      CommentRepository commentRepository) {
    this.blogPostRepository = blogPostRepository;
    this.commentRepository = commentRepository;
  }

  @RequestMapping("/login")
  public String login() {
    return "login";
  }

  @RequestMapping("/")
  public String index(@AuthenticationPrincipal UserDetails userDetails, Model model) {
    username = userDetails.getUsername();
    model.addAttribute("username", username);
    if (hasRole("ROLE_ADMIN")) {
      model.addAttribute("hasAdminRole", true);
    }
    return "index";
  }

  @RequestMapping("/post/{id}")
  public String post(@AuthenticationPrincipal UserDetails userDetails, Model model,
      @PathVariable String id) {
    username = userDetails.getUsername();
    if (hasRole("ROLE_ADMIN")) {
      model.addAttribute("hasAdminRole", true);
    }
    model.addAttribute("username", username);
    model.addAttribute("post", blogPostRepository.findOne(id));
    model.addAttribute("comments", commentRepository.findByPostIdOrderByCommentDateDesc(id));
    return "post";
  }

  @SuppressWarnings("unchecked")
  private boolean hasRole(String role) {
    Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder
        .getContext().getAuthentication().getAuthorities();
    boolean hasRole = false;
    for (GrantedAuthority authority : authorities) {
      hasRole = authority.getAuthority().equals(role);
      if (hasRole) {
        break;
      }
    }
    return hasRole;
  }


}
