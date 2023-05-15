package pl.mskreczko.blogcms.application.ports.out;

import pl.mskreczko.blogcms.application.domain.Comment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentPort {
    void save(Comment comment);
    Optional<Comment> findById(UUID commentId);
    List<Comment> findByPostId(UUID postId);
    void delete(Comment comment);
    void deleteByPostId(UUID postId);
}
