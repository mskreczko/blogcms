package pl.mskreczko.blogcms.application.ports.in.user;

import pl.mskreczko.blogcms.application.exceptions.EntityAlreadyExistsException;

public interface CreateUserUseCase {
    void createUser(String username) throws EntityAlreadyExistsException ;
}
