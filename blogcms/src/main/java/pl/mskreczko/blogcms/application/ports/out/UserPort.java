package pl.mskreczko.blogcms.application.ports.out;

import pl.mskreczko.blogcms.application.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserPort {
    void save(User user);
    Optional<User> loadById(UUID id);
    Optional<User> loadByUsername(String username);
}
