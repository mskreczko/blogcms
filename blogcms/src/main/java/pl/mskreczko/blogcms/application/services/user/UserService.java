package pl.mskreczko.blogcms.application.services.user;

import pl.mskreczko.blogcms.application.ports.in.user.CreateUserUseCase;
import pl.mskreczko.blogcms.application.ports.in.user.GetUserUseCase;

public interface UserService extends CreateUserUseCase, GetUserUseCase {

}
