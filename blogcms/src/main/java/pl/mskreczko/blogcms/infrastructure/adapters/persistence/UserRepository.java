package pl.mskreczko.blogcms.infrastructure.adapters.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mskreczko.blogcms.application.ports.out.UserPort;

@Component
@RequiredArgsConstructor
public class UserRepository implements UserPort {

    private final SpringDataUserRepository repository;
}
