package ru.netology.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.netology.model.Post;
import ru.netology.service.PostService;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Post> all() throws IOException {
        return this.service.all();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Post getById(@PathVariable long id) throws IOException  {

        return this.service.getById(id);

    }

    @RequestMapping(method = RequestMethod.POST)
    public Post save(@RequestBody Post post) throws IOException {

        return this.service.save(post);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean removeById(@PathVariable long id) {
        boolean result = this.service.removeById(id);
        return result;
    }
}
