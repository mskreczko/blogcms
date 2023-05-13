package pl.mskreczko.blogcms.application.services.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mskreczko.blogcms.application.domain.Comment;
import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.application.ports.out.CommentPort;
import pl.mskreczko.blogcms.application.ports.out.PostPort;
import pl.mskreczko.blogcms.application.services.mappers.CommentMapper;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.CommentDto;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.NewCommentDto;
import pl.mskreczko.blogcms.infrastructure.config.uuid.UUIDProvider;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class CommentServiceImpl implements CommentService {

    private final CommentPort commentPort;
    private final PostPort postPort;
    private final UUIDProvider uuidProvider;
    private final CommentMapper commentMapper;

    @Override
    public void createComment(UUID postId, NewCommentDto newCommentDto) throws NoSuchEntityException {
        final var post = postPort.loadById(postId).orElseThrow(NoSuchEntityException::new);

        Comment comment = commentMapper.newCommentDtoToComment(newCommentDto);
        comment.setId(uuidProvider.getUUID());
        comment.setPost(post);
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
                .map(commentMapper::commentToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void changeLikesCount(UUID commentId, Integer countChange) {
        var comment = commentPort.findById(commentId).orElseThrow(NoSuchEntityException::new);
        comment.changeLikesCount(countChange);
        commentPort.save(comment);
    }

    @Override
    @Transactional
    public void changeDislikesCount(UUID commentId, Integer countChange) {
        var comment = commentPort.findById(commentId).orElseThrow(NoSuchEntityException::new);
        comment.changeDislikesCount(countChange);
        commentPort.save(comment);
    }
}