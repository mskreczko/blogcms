package pl.mskreczko.blogcms.mappers;

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
import pl.mskreczko.blogcms.application.ports.out.UserPort;
import pl.mskreczko.blogcms.application.services.mappers.CommentMapper;
import pl.mskreczko.blogcms.application.services.mappers.CommentMapperConfiguration;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.NewCommentDto;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CommentMapperTest {

    @Mock
    UserPort userPort;
    @InjectMocks
    CommentMapperConfiguration commentMapperConfiguration;

    CommentMapper commentMapper;

    @BeforeEach
    void setup() {
        commentMapper = commentMapperConfiguration.commentMapper();
    }

    final UUID TEST_ID = UUID.fromString("0000-00-00-00-000000");

    @Test
    void commentToCommentDto_returnsCorrectCommentDto() {
        final var mockUser = new User(TEST_ID, "test");
        final var mockPost = new Post(TEST_ID, mockUser, "Test content", "Test title");
        final var mockComment = new Comment(TEST_ID, mockUser, mockPost, "Test content");

        final var dto = commentMapper.commentToCommentDto(mockComment);

        Assertions.assertEquals(mockUser.getUsername(), dto.author());
        Assertions.assertEquals(mockComment.getContent(), dto.content());
    }

    @Test
    void newCommentDtoToComment_throwsException() {
        Mockito.when(userPort.loadById(TEST_ID))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchEntityException.class,
                () -> commentMapper.newCommentDtoToComment(new NewCommentDto(TEST_ID, "test content")));
    }

    @Test
    void newCommentDtoToComment_returnsCorrectComment() {
        final var mockUser = new User(TEST_ID, "test");

        Mockito.when(userPort.loadById(TEST_ID))
                .thenReturn(Optional.of(mockUser));

        final var comment = commentMapper.newCommentDtoToComment(new NewCommentDto(TEST_ID, "Test content"));

        Assertions.assertEquals("test", comment.getAuthor().getUsername());
        Assertions.assertEquals("Test content", comment.getContent());
    }
}
