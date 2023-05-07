package pl.mskreczko.blogcms.application.services.user;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mskreczko.blogcms.application.ports.out.UserPort;
import pl.mskreczko.blogcms.infrastructure.config.uuid.UUIDProvider;

@Configuration
@RequiredArgsConstructor
public class UserConfiguration {

    private final UserPort userPort;
    private final UUIDProvider uuidProvider;

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userPort, uuidProvider);
    }
}
