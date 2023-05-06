package pl.mskreczko.blogcms.application.ports.in;

import java.util.UUID;

public interface CreateCommentUseCase {
    void createComment(UUID postId, UUID authorId, String content);
}
