package pl.mskreczko.blogcms.application.services.user;

import pl.mskreczko.blogcms.application.ports.in.user.CreateUser;
import pl.mskreczko.blogcms.application.ports.in.user.GetUser;

public interface UserService extends CreateUser, GetUser {

}
