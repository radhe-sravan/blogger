package com.blogger.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment implements Serializable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  private String postId;
  private String commentAsUser;
  @Column(name="comment", length = 100000)
  private String comment;
  @CreatedDate
  private Date commentDate;

  protected Comment() {

  }

  public Comment(String postId, String commentAsUser, String comment) {
    this.postId = postId;
    this.commentAsUser = commentAsUser;
    this.comment = comment;
  }

  public String getId() {
    return id;
  }

  public String getPostId() {
    return postId;
  }

  public String getCommentAsUser() {
    return commentAsUser;
  }

  public String getComment() {
    return comment;
  }

  public String getCommentDate() {
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMMM d, yyyy");
    SimpleDateFormat dateFormat2 = new SimpleDateFormat("hh:mm a");
    String convertedDate =
        dateFormat1.format(commentDate) + " at " + dateFormat2.format(commentDate);
    return convertedDate;
  }

}
