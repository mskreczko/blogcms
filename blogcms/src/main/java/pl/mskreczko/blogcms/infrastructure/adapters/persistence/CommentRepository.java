package pl.mskreczko.blogcms.infrastructure.adapters.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mskreczko.blogcms.application.domain.Comment;
import pl.mskreczko.blogcms.application.ports.out.CommentPort;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CommentRepository implements CommentPort {

    private final SpringDataCommentRepository repository;

    @Override
    public void save(Comment comment) {
        repository.save(comment);
    }

    public List<Comment> loadCommentByPostId(UUID postId) {
        return repository.findAllByPostId(postId);
    }
}
