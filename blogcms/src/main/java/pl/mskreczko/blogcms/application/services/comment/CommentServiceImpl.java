package pl.mskreczko.blogcms.application.services.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mskreczko.blogcms.application.domain.Comment;
import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.application.ports.out.CommentPort;
import pl.mskreczko.blogcms.application.ports.out.PostPort;
import pl.mskreczko.blogcms.application.ports.out.UserPort;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.CommentDto;
import pl.mskreczko.blogcms.infrastructure.config.uuid.UUIDProvider;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class CommentServiceImpl implements CommentService {

    private final CommentPort commentPort;
    private final PostPort postPort;
    private final UserPort userPort;
    private final UUIDProvider uuidProvider;

    @Override
    public void createComment(UUID postId, UUID authorId, String content) throws NoSuchEntityException {
        final var user = userPort.loadById(authorId).orElseThrow(NoSuchEntityException::new);
        final var post = postPort.loadById(postId).orElseThrow(NoSuchEntityException::new);

        Comment comment = new Comment();
        comment.setId(uuidProvider.getUUID());
        comment.setAuthor(user);
        comment.setPost(post);
        comment.setContent(content);

        commentPort.save(comment);
    }

    @Override
    public void deleteComment(UUID commentId) throws NoSuchEntityException {
        commentPort.findById(commentId).ifPresentOrElse(commentPort::delete, () -> {
            throw new NoSuchEntityException();
        });
    }

    @Override
    public List<CommentDto> getCommentsByPost(UUID postId) throws NoSuchEntityException {
        if (postPort.loadById(postId).isEmpty()) {
            throw new NoSuchEntityException();
        }

        return commentPort.findByPostId(postId).stream()
                .map((comment) -> new CommentDto(comment.getAuthor().getUsername(), comment.getContent(), comment.getCreatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public void changeLikesCount(UUID commentId, Integer countChange) {
        var comment = commentPort.findById(commentId).orElseThrow(NoSuchEntityException::new);
        comment.changeLikesCount(countChange);
        commentPort.save(comment);
    }

    @Override
    public void changeDislikesCount(UUID commentId, Integer countChange) {
        var comment = commentPort.findById(commentId).orElseThrow(NoSuchEntityException::new);
        comment.changeDislikesCount(countChange);
        commentPort.save(comment);
    }
}