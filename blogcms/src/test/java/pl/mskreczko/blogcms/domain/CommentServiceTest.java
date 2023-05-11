package pl.mskreczko.blogcms.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
import pl.mskreczko.blogcms.application.services.comment.CommentConfiguration;
import pl.mskreczko.blogcms.application.services.comment.CommentService;
import pl.mskreczko.blogcms.infrastructure.config.uuid.UUIDProvider;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentPort commentPort;
    @Mock
    private UserPort userPort;
    @Mock
    private PostPort postPort;
    @Mock
    private UUIDProvider uuidProvider;
    @InjectMocks
    private CommentConfiguration commentConfiguration;
    private CommentService commentService;
    @BeforeEach
    void setup() {
        commentService = commentConfiguration.commentService();
    }

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
        Mockito.when(uuidProvider.getUUID())
                .thenReturn(TEST_ID);

        commentService.createComment(TEST_ID, TEST_ID, "Test content");

        Mockito.verify(commentPort).save(
                new Comment(TEST_ID, mockUser, mockPost, "Test content")
        );
    }

    @Test
    void deleteComment_throwsOnCommentLookup() {
        Mockito.when(commentPort.findById(TEST_ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchEntityException.class,
                () -> commentService.deleteComment(TEST_ID));
    }

    @Test
    void changeThumbsUpCount_throwsIllegalArgumentException() {
        var mockComment = new Comment(TEST_ID, null, null, "Test content");
        Mockito.when(commentPort.findById(TEST_ID)).thenReturn(Optional.of(mockComment));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> commentService.changeThumbsUpCount(TEST_ID, 0));
    }

    @Test
    void changeThumbsDownCount_throwsIllegalArgumentException() {
        var mockComment = new Comment(TEST_ID, null, null, "Test content");
        Mockito.when(commentPort.findById(TEST_ID)).thenReturn(Optional.of(mockComment));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> commentService.changeThumbsDownCount(TEST_ID, 5));
    }

    @Test
    void changeThumbsUpCount_throwsOnCommentLookup() {
        Mockito.when(commentPort.findById(TEST_ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchEntityException.class,
                () -> commentService.changeThumbsUpCount(TEST_ID, 1));
    }

    @Test
    void changeThumbsUpCount_changesCount() {
        var mockComment = new Comment(TEST_ID, null, null, "Test content");
        Mockito.when(commentPort.findById(TEST_ID)).thenReturn(Optional.of(mockComment));

        commentService.changeThumbsUpCount(TEST_ID, 1);

        Assertions.assertEquals(1, mockComment.getLikesCount());
    }

    @Test
    void changeThumbsDownCount_throwsOnCommentLookup() {
        Mockito.when(commentPort.findById(TEST_ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchEntityException.class,
                () -> commentService.changeThumbsDownCount(TEST_ID, -1));
    }

    @Test
    void changeThumbsDownCount_changesCount() {
        var mockComment = new Comment(TEST_ID, null, null, "Test content");
        Mockito.when(commentPort.findById(TEST_ID)).thenReturn(Optional.of(mockComment));

        commentService.changeThumbsDownCount(TEST_ID, -1);

        Assertions.assertEquals(-1, mockComment.getDislikesCount());
    }

    //@Test
    //void deleteComment_deletesComment() {
    //    final var mockUser = new User(TEST_ID, "test");
    //    final var mockPost = new Post(TEST_ID, mockUser, "Test title", "Test content");
    //    final var mockComment = new Comment(TEST_ID, mockUser, mockPost, "test content");

    //    Mockito.when(commentPort.findById(TEST_ID))
    //            .thenReturn(Optional.of(mockComment));

    //    Mockito.verify(commentPort).delete(mockComment);
    //}
}
