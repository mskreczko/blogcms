package pl.mskreczko.blogcms.infrastructure.adapters.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.application.ports.in.comment.CreateCommentUseCase;
import pl.mskreczko.blogcms.application.ports.in.comment.DeleteCommentUseCase;
import pl.mskreczko.blogcms.application.ports.in.comment.GetCommentsByPostUseCase;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.NewCommentDto;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CreateCommentUseCase createCommentUseCase;
    private final DeleteCommentUseCase deleteCommentUseCase;
    private final GetCommentsByPostUseCase getCommentsByPostUseCase;

    @GetMapping
    public ResponseEntity<?> getCommentsByPost(@PathVariable("postId") UUID postId) {
        try {
            return ResponseEntity.ok(getCommentsByPostUseCase.getCommentsByPost(postId));
        } catch (NoSuchEntityException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createComment(@PathVariable("postId") UUID postId, @RequestBody NewCommentDto newCommentDto) {
        try {
            createCommentUseCase.createComment(postId, newCommentDto.authorId(), newCommentDto.content());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NoSuchEntityException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") UUID commentId) {
        try {
            deleteCommentUseCase.deleteComment(commentId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NoSuchEntityException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
