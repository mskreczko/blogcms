package pl.mskreczko.blogcms.infrastructure.adapters.persistence.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mskreczko.blogcms.application.domain.Comment;
import pl.mskreczko.blogcms.application.ports.out.CommentPort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CommentRepository implements CommentPort {

    private final SpringDataCommentRepository repository;

    @Override
    public void save(Comment comment) {
        repository.save(comment);
    }

    @Override
    public Optional<Comment> findById(UUID commentId) {
        return repository.findById(commentId);
    }

    @Override
    public List<Comment> findByPostId(UUID postId) {
        return repository.findAllByPostId(postId);
    }

    @Override
    public void delete(Comment comment) {
        repository.delete(comment);
    }

    @Override
    public void deleteByPostId(UUID postId) {
        repository.deleteByPostId(postId);
    }
}
