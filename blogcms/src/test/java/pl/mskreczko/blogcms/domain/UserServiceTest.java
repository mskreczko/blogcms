package pl.mskreczko.blogcms.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mskreczko.blogcms.application.domain.User;
import pl.mskreczko.blogcms.application.exceptions.EntityAlreadyExistsException;
import pl.mskreczko.blogcms.application.ports.out.UserPort;
import pl.mskreczko.blogcms.application.services.user.UserConfiguration;
import pl.mskreczko.blogcms.application.services.user.UserService;
import pl.mskreczko.blogcms.infrastructure.config.uuid.UUIDProvider;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserPort userPort;
    @Mock
    private UUIDProvider uuidProvider;
    @InjectMocks
    private UserConfiguration userConfiguration;
    private UserService userService;

    @BeforeEach
    void setup() {
        userService = userConfiguration.userService();
    }

    private final UUID TEST_ID = UUID.fromString("0000-00-00-00-000000");

    @Test
    void createUser_throwsOnAlreadyExistingUser() {
        Mockito.when(userPort.loadByUsername("test"))
                .thenReturn(Optional.of(new User()));

        Assertions.assertThrows(EntityAlreadyExistsException.class,
                () -> userService.createUser("test")
        );
    }

    @Test
    void createUser_createsUser() {
        Mockito.when(userPort.loadByUsername("test"))
                .thenReturn(Optional.empty());
        Mockito.when(uuidProvider.getUUID()).thenReturn(TEST_ID);

        userService.createUser("test");

        Mockito.verify(userPort).save(
                new User(TEST_ID,"test")
        );
    }
}
