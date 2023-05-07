package pl.mskreczko.blogcms.application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mskreczko.blogcms.application.domain.User;
import pl.mskreczko.blogcms.application.exceptions.EntityAlreadyExistsException;
import pl.mskreczko.blogcms.application.ports.in.user.CreateUserUseCase;
import pl.mskreczko.blogcms.application.ports.out.UserPort;
import pl.mskreczko.blogcms.infrastructure.config.uuid.UUIDProvider;

@Service
@RequiredArgsConstructor
class UserService implements CreateUserUseCase {

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
}
