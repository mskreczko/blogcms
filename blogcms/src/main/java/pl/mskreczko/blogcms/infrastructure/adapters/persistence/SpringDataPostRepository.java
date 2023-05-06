package pl.mskreczko.blogcms.infrastructure.adapters.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.mskreczko.blogcms.application.domain.Post;

import java.util.UUID;

public interface SpringDataPostRepository extends MongoRepository<Post, UUID> {
}
