package pl.mskreczko.blogcms.infrastructure.adapters.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.application.services.post.PostService;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.NewPostDto;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody NewPostDto newPostDto) {
        try {
            postService.createPost(newPostDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NoSuchEntityException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<?> deletePost(@PathVariable("postId")UUID postId) {
        try {
            postService.deletePost(postId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NoSuchEntityException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("{postId}")
    public ResponseEntity<?> getPost(@PathVariable("postId") UUID postId) {
        try {
            return ResponseEntity.ok(postService.getPost(postId));
        } catch (NoSuchEntityException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<?> getPostsByAuthor(@RequestParam("authorId") UUID authorId) {
        try {
            return ResponseEntity.ok(postService.getPostsByAuthor(authorId));
        } catch (NoSuchEntityException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
