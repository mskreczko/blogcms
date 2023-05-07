package pl.mskreczko.blogcms.infrastructure.adapters.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.application.ports.in.post.CreatePostUseCase;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.NewPostDto;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final CreatePostUseCase createPostUseCase;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody NewPostDto newPostDto) {
        try {
            createPostUseCase.createPost(newPostDto.authorId(), newPostDto.title(), newPostDto.content());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NoSuchEntityException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
