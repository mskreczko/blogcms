package pl.mskreczko.blogcms.infrastructure.adapters.persistence.post;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mskreczko.blogcms.application.domain.Post;
import pl.mskreczko.blogcms.application.domain.User;

import java.util.List;
import java.util.UUID;

public interface SpringDataPostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByAuthor(User author);
}
