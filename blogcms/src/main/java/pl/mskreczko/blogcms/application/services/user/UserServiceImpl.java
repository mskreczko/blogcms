package pl.mskreczko.blogcms.application.services.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mskreczko.blogcms.application.domain.User;
import pl.mskreczko.blogcms.application.exceptions.EntityAlreadyExistsException;
import pl.mskreczko.blogcms.application.ports.out.UserPort;
import pl.mskreczko.blogcms.infrastructure.config.uuid.UUIDProvider;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserPort userPort;
    private final UUIDProvider uuidProvider;

    @Override
    public void createUser(String username) throws EntityAlreadyExistsException {
        userPort.loadByUsername(username).ifPresent((user) -> { throw new EntityAlreadyExistsException(); });
        User user = new User();
        user.setId(uuidProvider.getUUID());
        user.setUsername(username);

        userPort.save(user);
    }

    @Override
    public Optional<User> getUser(UUID id) {
        return userPort.loadById(id);
    }
}
