package pl.mskreczko.blogcms.infrastructure.adapters.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.application.services.comment.CommentService;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.NewCommentDto;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
@RequiredArgsConstructor
class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<?> getCommentsByPost(@PathVariable("postId") UUID postId) {
        try {
            return ResponseEntity.ok(commentService.getCommentsByPost(postId));
        } catch (NoSuchEntityException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createComment(@PathVariable("postId") UUID postId, @RequestBody NewCommentDto newCommentDto) {
        try {
            commentService.createComment(postId, newCommentDto.authorId(), newCommentDto.content());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NoSuchEntityException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") UUID commentId) {
        try {
            commentService.deleteComment(commentId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NoSuchEntityException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PatchMapping("{commentId}/likes")
    public ResponseEntity<?> changeLikesCount(@PathVariable("commentId") UUID commentId, @RequestParam("countChange") Integer countChange) {
        try {
            commentService.changeLikesCount(commentId, countChange);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NoSuchEntityException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @PatchMapping("{commentId}/dislikes")
    public ResponseEntity<?> changeDislikesCount(@PathVariable("commentId") UUID commentId, @RequestParam("countChange") Integer countChange) {
        try {
            commentService.changeDislikesCount(commentId, countChange);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NoSuchEntityException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }
}