package pl.mskreczko.blogcms.application.ports.in.post;

import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;

import java.util.UUID;

public interface DeletePostUseCase {
    void deletePost(UUID postId) throws NoSuchEntityException;
}
