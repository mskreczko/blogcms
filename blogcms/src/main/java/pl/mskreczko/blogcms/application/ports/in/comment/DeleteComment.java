package pl.mskreczko.blogcms.application.ports.in.comment;

import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;

import java.util.UUID;

public interface DeleteComment {
    void deleteComment(UUID commentId) throws NoSuchEntityException;
}
