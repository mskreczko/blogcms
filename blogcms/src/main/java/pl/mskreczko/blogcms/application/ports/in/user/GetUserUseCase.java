package pl.mskreczko.blogcms.application.ports.in.user;

import pl.mskreczko.blogcms.application.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface GetUserUseCase {
    Optional<User> getUser(UUID id);
}
