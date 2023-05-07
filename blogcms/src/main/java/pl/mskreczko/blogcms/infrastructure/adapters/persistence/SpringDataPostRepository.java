package pl.mskreczko.blogcms.infrastructure.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mskreczko.blogcms.application.domain.Post;

import java.util.UUID;

public interface SpringDataPostRepository extends JpaRepository<Post, UUID> {
}
