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
public class BlogPost implements Serializable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  private String title;
  @Column(name="content", length = 100000)
  private String content;
  @CreatedDate
  private Date createdDate;

  public BlogPost() {

  }

  public BlogPost(String user, String title, String content) {
    this.title = title;
    this.content = content;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public String getCreatedDate() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM, yyyy");
    String convertedDate = dateFormat.format(createdDate);
    return convertedDate;
  }

}
