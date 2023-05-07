package pl.mskreczko.blogcms.infrastructure.adapters.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.application.ports.in.CreateCommentUseCase;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.NewCommentDto;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CreateCommentUseCase createCommentUseCase;

    @PostMapping
    public ResponseEntity<?> createComment(@PathVariable("postId") UUID postId, @RequestBody NewCommentDto newCommentDto) {
        try {
            createCommentUseCase.createComment(postId, newCommentDto.authorId(), newCommentDto.content());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NoSuchEntityException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
