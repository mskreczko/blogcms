package pl.mskreczko.blogcms.application.ports.in.post;

import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;

import java.util.UUID;

public interface CreatePostUseCase {
    void createPost(UUID authorId, String title, String content) throws NoSuchEntityException;
}
