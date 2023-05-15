package pl.mskreczko.blogcms.application.ports.in.comment;

import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.CommentDto;

import java.util.List;
import java.util.UUID;

public interface GetCommentsByPost {
    List<CommentDto> getCommentsByPost(UUID postId) throws NoSuchEntityException;
}
