package pl.mskreczko.blogcms.application.ports.in;

import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;

import java.util.UUID;

public interface CreateCommentUseCase {
    void createComment(UUID postId, UUID authorId, String content) throws NoSuchEntityException;
}
