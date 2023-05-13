package pl.mskreczko.blogcms.application.services.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.application.ports.out.CommentPort;
import pl.mskreczko.blogcms.application.ports.out.PostPort;
import pl.mskreczko.blogcms.application.ports.out.UserPort;
import pl.mskreczko.blogcms.application.services.mappers.PostMapper;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.NewPostDto;
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
    private final PostMapper postMapper;

    @Override
    public void createPost(NewPostDto newPostDto) throws NoSuchEntityException {
        var post = postMapper.newPostDtoToPost(newPostDto);
        post.setId(uuidProvider.getUUID());
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

        return postMapper.postToPostDto(post, comments);
    }

    @Override
    public List<PostDto> getPostsByAuthor(UUID authorId) throws NoSuchEntityException {
        final var author = userPort.loadById(authorId).orElseThrow(NoSuchEntityException::new);
        final var posts = postPort.loadByPostAuthor(author);
        return posts.stream().map((post) -> postMapper.postToPostDto(post, commentPort.findByPostId(post.getId())))
                .collect(Collectors.toList());
    }
}
