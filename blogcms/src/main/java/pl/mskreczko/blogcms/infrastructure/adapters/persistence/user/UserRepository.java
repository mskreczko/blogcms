package pl.mskreczko.blogcms.infrastructure.adapters.persistence.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mskreczko.blogcms.application.domain.User;
import pl.mskreczko.blogcms.application.ports.out.UserPort;
import pl.mskreczko.blogcms.infrastructure.adapters.persistence.user.SpringDataUserRepository;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserRepository implements UserPort {

    private final SpringDataUserRepository repository;
    @Override
    public void save(User user) {
        repository.save(user);
    }
    @Override
    public Optional<User> loadById(UUID id) {
        return repository.findById(id);
    }
    @Override
    public Optional<User> loadByUsername(String username) {
        return repository.findByUsername(username);
    }
}
