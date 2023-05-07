package pl.mskreczko.blogcms.application.services.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mskreczko.blogcms.application.domain.Post;
import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.application.ports.out.CommentPort;
import pl.mskreczko.blogcms.application.ports.out.PostPort;
import pl.mskreczko.blogcms.application.ports.out.UserPort;
import pl.mskreczko.blogcms.infrastructure.config.uuid.UUIDProvider;

import java.util.UUID;

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
}
