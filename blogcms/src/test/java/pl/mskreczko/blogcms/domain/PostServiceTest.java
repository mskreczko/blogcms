package pl.mskreczko.blogcms.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mskreczko.blogcms.application.domain.Post;
import pl.mskreczko.blogcms.application.domain.User;
import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.application.ports.out.PostPort;
import pl.mskreczko.blogcms.application.ports.out.UserPort;
import pl.mskreczko.blogcms.application.services.post.PostConfiguration;
import pl.mskreczko.blogcms.application.services.post.PostService;
import pl.mskreczko.blogcms.infrastructure.config.uuid.UUIDProvider;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @Mock
    private PostPort postPort;
    @Mock
    private UserPort userPort;
    @Mock
    private UUIDProvider uuidProvider;
    @InjectMocks
    private PostConfiguration postConfiguration;
    private PostService postService;

    @BeforeEach
    void setup() {
        postService = postConfiguration.postService();
    }

    private final UUID TEST_ID = UUID.fromString("0000-00-00-00-000000");

    @Test
    void createPost_throwsOnUserLookup() {
        Mockito.when(userPort.loadById(TEST_ID))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchEntityException.class,
                () -> postService.createPost(TEST_ID, "Test title", "Test content")
        );
    }

    @Test
    void createPost_savesPost() {
        final var mockUser = new User(TEST_ID, "test");

        Mockito.when(userPort.loadById(TEST_ID))
                .thenReturn(Optional.of(mockUser));
        Mockito.when(uuidProvider.getUUID()).thenReturn(TEST_ID);

        postService.createPost(TEST_ID, "Test title", "Test content");

        Mockito.verify(postPort).save(
                new Post(TEST_ID, mockUser, "Test content", "Test title")
        );
    }
}
