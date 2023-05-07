package pl.mskreczko.blogcms.application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mskreczko.blogcms.application.domain.Post;
import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.application.ports.in.post.CreatePostUseCase;
import pl.mskreczko.blogcms.application.ports.out.PostPort;
import pl.mskreczko.blogcms.application.ports.out.UserPort;
import pl.mskreczko.blogcms.infrastructure.config.uuid.UUIDProvider;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class PostService implements CreatePostUseCase {

    private final PostPort postPort;
    private final UserPort userPort;

    private final UUIDProvider uuidProvider;
    @Transactional
    @Override
    public void createPost(UUID authorId, String title, String content) throws NoSuchElementException {
        final var user = userPort.loadById(authorId).orElseThrow(NoSuchEntityException::new);
        Post post = new Post();
        post.setId(uuidProvider.getUUID());
        post.setAuthor(user);
        post.setContent(content);
        post.setTitle(title);
        postPort.save(post);
    }
}
