package pl.mskreczko.blogcms.infrastructure.adapters.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.mskreczko.blogcms.application.domain.Comment;

import java.util.UUID;

public interface SpringDataCommentRepository extends MongoRepository<Comment, UUID> {
}
