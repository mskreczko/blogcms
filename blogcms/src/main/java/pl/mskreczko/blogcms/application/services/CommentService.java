package pl.mskreczko.blogcms.application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mskreczko.blogcms.application.domain.Comment;
import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.application.ports.in.CreateCommentUseCase;
import pl.mskreczko.blogcms.application.ports.out.CommentPort;
import pl.mskreczko.blogcms.application.ports.out.PostPort;
import pl.mskreczko.blogcms.application.ports.out.UserPort;
import pl.mskreczko.blogcms.infrastructure.config.uuid.UUIDProvider;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService implements CreateCommentUseCase {

    private final CommentPort commentPort;
    private final PostPort postPort;
    private final UserPort userPort;
    private final UUIDProvider uuidProvider;

    @Override
    public void createComment(UUID postId, UUID authorId, String content) throws NoSuchElementException {
        final var user = userPort.loadById(authorId).orElseThrow(NoSuchEntityException::new);
        final var post = postPort.loadById(postId).orElseThrow(NoSuchEntityException::new);

        Comment comment = new Comment();
        comment.setId(uuidProvider.getUUID());
        comment.setAuthor(user);
        comment.setPost(post);
        comment.setContent(content);

        commentPort.save(comment);
    }
}
