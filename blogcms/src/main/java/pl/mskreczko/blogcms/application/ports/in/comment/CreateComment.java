package pl.mskreczko.blogcms.application.ports.in.comment;

import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.NewCommentDto;

import java.util.UUID;

public interface CreateComment {
    void createComment(UUID postId, NewCommentDto newCommentDto) throws NoSuchEntityException;
}
