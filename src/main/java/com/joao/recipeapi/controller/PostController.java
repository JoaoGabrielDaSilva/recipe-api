package com.joao.recipeapi.controller;

import com.joao.recipeapi.dto.CreatePostRequestBody;
import com.joao.recipeapi.model.Post;
import com.joao.recipeapi.model.User;
import com.joao.recipeapi.repository.PostRepository;
import com.joao.recipeapi.repository.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/posts")
public class PostController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public List<Post> getPosts() {
        return this.postRepository.findAll();
    }

    @PostMapping()
    public ResponseEntity<Post> createPost(@RequestBody CreatePostRequestBody body) {

        Post newPost = new Post();
        newPost.setDescription(body.getDescription());

        Optional<User> author = userRepository.findById(body.getAuthorId());

        if (!author.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        newPost.setAuthor(author.get());

        this.postRepository.save(newPost);

        return ResponseEntity.ok(newPost);
    }
}
