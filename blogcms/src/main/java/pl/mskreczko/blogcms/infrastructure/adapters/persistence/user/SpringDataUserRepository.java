package pl.mskreczko.blogcms.infrastructure.adapters.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mskreczko.blogcms.application.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
