package pl.mskreczko.blogcms.infrastructure.adapters.persistence.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mskreczko.blogcms.application.domain.Comment;

import java.util.List;
import java.util.UUID;

public interface SpringDataCommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findAllByPostId(UUID postId);
    void deleteByPostId(UUID postId);
}
