package pl.mskreczko.blogcms.application.ports.in;

import java.util.UUID;

public interface CreatePostUseCase {
    void createPost(UUID authorId, String title, String content);
}
