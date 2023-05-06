package pl.mskreczko.blogcms.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mskreczko.blogcms.application.domain.Comment;
import pl.mskreczko.blogcms.application.domain.Post;
import pl.mskreczko.blogcms.application.domain.User;
import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.application.ports.out.CommentPort;
import pl.mskreczko.blogcms.application.ports.out.PostPort;
import pl.mskreczko.blogcms.application.ports.out.UserPort;
import pl.mskreczko.blogcms.application.services.CommentService;
import pl.mskreczko.blogcms.infrastructure.config.uuid.UUIDProvider;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentPort commentPort;
    @Mock
    private UserPort userPort;
    @Mock
    private PostPort postPort;
    @Mock
    private UUIDProvider uuidProvider;
    @InjectMocks
    private CommentService commentService;

    private final UUID TEST_ID = UUID.fromString("0000-00-00-00-000000");

    @Test
    void createComment_throwsOnUserAndPostLookup() {
        Mockito.when(userPort.loadById(TEST_ID))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchEntityException.class,
                () -> commentService.createComment(TEST_ID, TEST_ID, "test")
        );
    }

    @Test
    void createComment_savesComment() {
        final var mockUser = new User(TEST_ID, "test");
        final var mockPost = new Post(TEST_ID, mockUser, "Test title", "Test content");

        Mockito.when(userPort.loadById(TEST_ID))
                .thenReturn(Optional.of(mockUser));
        Mockito.when(postPort.loadById(TEST_ID))
                .thenReturn(Optional.of(mockPost));
        Mockito.when(uuidProvider.getUUID()).thenReturn(TEST_ID);

        commentService.createComment(TEST_ID, TEST_ID, "Test content");

        Mockito.verify(commentPort).save(
                new Comment(TEST_ID, mockUser, mockPost, "Test content")
        );
    }
}
