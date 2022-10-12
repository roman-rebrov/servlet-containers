package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Stub
@Repository
public class PostRepository {

    private Map<Long, Post> repo = new ConcurrentHashMap<>();
    private long idCounter = 1;

    private synchronized long IdCreator() {
        return this.idCounter++;
    }

    public List<Post> all() {

        List<Post> list = new ArrayList<>();

        for (Post post : repo.values()) {
            list.add(post);
        }
        return list;
    }

    public Optional<Post> getById(long id) {

        Post post = repo.get(id);
        if (post == null) {
            return Optional.empty();
        }
        return Optional.of(post);
    }

    public synchronized void update(long key, Post value) {
        repo.put(key, value);
    }

    public synchronized Post save(Post post) {
        long newId = this.IdCreator();
        Post newPost = new Post(newId, post.getContent());
        repo.put(newId, newPost);
        return newPost;
    }

    public boolean removeById(long id) {
        if (repo.containsKey(id)) {
            repo.remove(id);
            return true;
        }
        return false;
    }
}
