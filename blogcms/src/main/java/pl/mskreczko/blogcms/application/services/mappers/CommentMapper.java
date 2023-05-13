package pl.mskreczko.blogcms.application.services.mappers;

import pl.mskreczko.blogcms.application.domain.Comment;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.CommentDto;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.NewCommentDto;

public interface CommentMapper {
    CommentDto commentToCommentDto(Comment comment);
    Comment newCommentDtoToComment(NewCommentDto newCommentDto);
}
