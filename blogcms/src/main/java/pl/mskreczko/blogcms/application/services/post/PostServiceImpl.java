package pl.mskreczko.blogcms.application.services.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mskreczko.blogcms.application.domain.Post;
import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.application.ports.out.CommentPort;
import pl.mskreczko.blogcms.application.ports.out.PostPort;
import pl.mskreczko.blogcms.application.ports.out.UserPort;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.CommentDto;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.PostDto;
import pl.mskreczko.blogcms.infrastructure.config.uuid.UUIDProvider;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class PostServiceImpl implements PostService {
    private final PostPort postPort;
    private final CommentPort commentPort;
    private final UserPort userPort;

    private final UUIDProvider uuidProvider;

    @Override
    public void createPost(UUID authorId, String title, String content) throws NoSuchEntityException {
        final var user = userPort.loadById(authorId).orElseThrow(NoSuchEntityException::new);
        Post post = new Post();
        post.setId(uuidProvider.getUUID());
        post.setAuthor(user);
        post.setContent(content);
        post.setTitle(title);
        postPort.save(post);
    }

    @Transactional
    @Override
    public void deletePost(UUID postId) throws NoSuchEntityException {
        if (postPort.loadById(postId).isEmpty()) {
            throw new NoSuchEntityException();
        }
        commentPort.deleteByPostId(postId);
        postPort.deleteById(postId);
    }

    @Override
    public PostDto getPost(UUID postId) throws NoSuchEntityException {
        final var post = postPort.loadById(postId).orElseThrow(NoSuchEntityException::new);
        final var comments = commentPort.findByPostId(postId);

        return new PostDto(post.getAuthor().getUsername(), post.getTitle(), post.getContent(),
                comments.stream().map((comment) -> new CommentDto(comment.getAuthor().getUsername(),
                        comment.getContent(), comment.getCreatedAt(), comment.getLikesCount(),
                        comment.getDislikesCount())).collect(Collectors.toList()));
    }

    @Override
    public List<PostDto> getPostsByAuthor(UUID authorId) throws NoSuchEntityException {
        final var author = userPort.loadById(authorId).orElseThrow(NoSuchEntityException::new);
        final var posts = postPort.loadByPostAuthor(author);
        return posts.stream().map((post) -> new PostDto(post.getAuthor().getUsername(), post.getTitle(), post.getContent(),
                commentPort.findByPostId(post.getId()).stream().map((comment) -> new CommentDto(
                        comment.getAuthor().getUsername(), comment.getContent(), comment.getCreatedAt(),
                        comment.getLikesCount(), comment.getDislikesCount())).collect(Collectors.toList()))).collect(Collectors.toList());
    }
}
