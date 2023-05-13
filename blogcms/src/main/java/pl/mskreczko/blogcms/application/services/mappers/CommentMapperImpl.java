package pl.mskreczko.blogcms.application.services.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mskreczko.blogcms.application.domain.Comment;
import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.application.ports.out.UserPort;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.CommentDto;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.NewCommentDto;

@RequiredArgsConstructor
@Service
class CommentMapperImpl implements CommentMapper {

    private final UserPort userPort;

    @Override
    public CommentDto commentToCommentDto(Comment comment) {
        return new CommentDto(comment.getAuthor().getUsername(), comment.getContent(),
                comment.getCreatedAt(), comment.getLikesCount(), comment.getDislikesCount());
    }

    @Override
    public Comment newCommentDtoToComment(NewCommentDto newCommentDto) {
        final var user = userPort.loadById(newCommentDto.authorId()).orElseThrow(NoSuchEntityException::new);
        Comment comment = new Comment();
        comment.setAuthor(user);
        comment.setContent(newCommentDto.content());
        return comment;
    }
}
