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
import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.application.ports.out.CommentPort;
import pl.mskreczko.blogcms.application.services.comment.CommentConfiguration;
import pl.mskreczko.blogcms.application.services.comment.CommentService;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentPort commentPort;
    @InjectMocks
    private CommentConfiguration commentConfiguration;
    private CommentService commentService;
    @BeforeEach
    void setup() {
        commentService = commentConfiguration.commentService();
    }

    private final UUID TEST_ID = UUID.fromString("0000-00-00-00-000000");

    @Test
    void changeLikesCount_throwsIllegalArgumentException() {
        var mockComment = new Comment(TEST_ID, null, null, "Test content");
        Mockito.when(commentPort.findById(TEST_ID)).thenReturn(Optional.of(mockComment));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> commentService.changeLikesCount(TEST_ID, 0));
    }

    @Test
    void changeDislikesCount_throwsIllegalArgumentException() {
        var mockComment = new Comment(TEST_ID, null, null, "Test content");
        Mockito.when(commentPort.findById(TEST_ID)).thenReturn(Optional.of(mockComment));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> commentService.changeDislikesCount(TEST_ID, 5));
    }

    @Test
    void changeLikesCount_throwsOnCommentLookup() {
        Mockito.when(commentPort.findById(TEST_ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchEntityException.class,
                () -> commentService.changeLikesCount(TEST_ID, 1));
    }

    @Test
    void changeLikesCount_changesCount() {
        var mockComment = new Comment(TEST_ID, null, null, "Test content");
        Mockito.when(commentPort.findById(TEST_ID)).thenReturn(Optional.of(mockComment));

        commentService.changeLikesCount(TEST_ID, 1);

        Assertions.assertEquals(1, mockComment.getLikesCount());
    }

    @Test
    void changeDislikesCount_changesCount() {
        var mockComment = new Comment(TEST_ID, null, null, "Test content");
        Mockito.when(commentPort.findById(TEST_ID)).thenReturn(Optional.of(mockComment));

        commentService.changeDislikesCount(TEST_ID, -1);

        Assertions.assertEquals(-1, mockComment.getDislikesCount());
    }

    @Test
    void changeDislikesCount_throwsOnCommentLookup() {
        Mockito.when(commentPort.findById(TEST_ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchEntityException.class,
                () -> commentService.changeDislikesCount(TEST_ID, -1));
    }
}
