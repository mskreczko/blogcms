package pl.mskreczko.blogcms.infrastructure.adapters.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.mskreczko.blogcms.application.domain.User;

import java.util.UUID;

public interface SpringDataUserRepository extends MongoRepository<User, UUID> {
}
