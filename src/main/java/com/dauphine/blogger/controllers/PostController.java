package com.dauphine.blogger.controllers;

import com.dauphine.blogger.model.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/posts")
@Tag(name = "Post API")
public class PostController {

    private final List<Post> temporaryPosts;

    public PostController() {
        this.temporaryPosts = new ArrayList<>();
        // Exemple de post initial
        this.temporaryPosts.add(new Post(UUID.randomUUID(), "First Post", "Content here", LocalDateTime.now(), UUID.randomUUID()));
    }

    @GetMapping
    @Operation(summary = "Retrieve all posts ordered by creation date")
    public List<Post> retrieveAllPosts() {
        return temporaryPosts;
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Retrieve all posts per a category")
    public List<Post> retrievePostsByCategory(@PathVariable UUID categoryId) {
        return temporaryPosts.stream()
                .filter(p -> p.getCategoryId().equals(categoryId))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a post by id")
    public Post retrievePostById(@PathVariable UUID id) {
        return temporaryPosts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    @Operation(summary = "Create a new post")
    public Post createPost(@RequestParam String title, @RequestParam String content, @RequestParam UUID categoryId) {
        Post post = new Post(UUID.randomUUID(), title, content, LocalDateTime.now(), categoryId);
        temporaryPosts.add(post);
        return post;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing post")
    public Post updatePost(@PathVariable UUID id, @RequestParam String title, @RequestParam String content) {
        for (Post post : temporaryPosts) {
            if (post.getId().equals(id)) {
                post.setTitle(title);
                post.setContent(content);
                return post;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing post")
    public void deletePost(@PathVariable UUID id) {
        temporaryPosts.removeIf(p -> p.getId().equals(id));
    }
}