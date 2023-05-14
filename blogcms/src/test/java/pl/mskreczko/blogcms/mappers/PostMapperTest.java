package pl.mskreczko.blogcms.mappers;

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
import pl.mskreczko.blogcms.application.ports.out.UserPort;
import pl.mskreczko.blogcms.application.services.mappers.CommentMapper;
import pl.mskreczko.blogcms.application.services.mappers.PostMapper;
import pl.mskreczko.blogcms.application.services.mappers.PostMapperConfiguration;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.NewPostDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class PostMapperTest {

    @Mock
    UserPort userPort;
    @Mock
    CommentMapper commentMapper;
    @InjectMocks
    PostMapperConfiguration postMapperConfiguration;
    PostMapper postMapper;

    @BeforeEach
    void setup() {
        postMapper = postMapperConfiguration.postMapper();
    }

    final UUID TEST_ID = UUID.fromString("0000-00-00-00-000000");

    @Test
    void newPostDtoToPost_throwsException() {
        Mockito.when(userPort.loadById(TEST_ID))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchEntityException.class,
                () -> postMapper.newPostDtoToPost(new NewPostDto(TEST_ID, "Test title", "Test content")));
    }

    @Test
    void newPostDtoToPost_returnsCorrectPost() {
        final var mockUser = new User(TEST_ID, "test");
        Mockito.when(userPort.loadById(TEST_ID))
                .thenReturn(Optional.of(mockUser));

        final var post = postMapper.newPostDtoToPost(new NewPostDto(TEST_ID, "Test title", "Test content"));

        Assertions.assertEquals(mockUser.getUsername(), post.getAuthor().getUsername());
        Assertions.assertEquals("Test title", post.getTitle());
        Assertions.assertEquals("Test content", post.getContent());
    }

    @Test
    void postToPostDto_returnsCorrectPostDto() {
        final var mockUser = new User(TEST_ID, "test");
        final var mockPost = new Post(TEST_ID, mockUser, "Test content", "Test title");

        final var postDto = postMapper.postToPostDto(mockPost, List.of());

        Assertions.assertEquals("test", postDto.author());
        Assertions.assertEquals("Test title", postDto.title());
        Assertions.assertEquals("Test content", postDto.content());
        Assertions.assertTrue(postDto.comments().isEmpty());
    }
}
