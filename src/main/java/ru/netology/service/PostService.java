package ru.netology.service;

import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
  private final PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public List<Post> all() {
    return repository.all();
  }

  public Post getById(long id) {
    return repository.getById(id).orElseThrow(NotFoundException::new);
  }

  public Post save(Post post) {
    long id  = post.getId();
    if (id == 0) {
      return repository.save(post);
    }
    Optional<Post> p = repository.getById(id);
    if (!p.isEmpty()){
      repository.update(id, post);
      return post;
    }
    return null;
  }

  public boolean removeById(long id) {
    return repository.removeById(id);
  }
}

