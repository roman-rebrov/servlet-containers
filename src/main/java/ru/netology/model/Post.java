package ru.netology.model;

public class Post {
  final private long id;
  final private String content;

  public Post(long id, String content) {
    this.id = id;
    this.content = content;
  }

  public long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

}
