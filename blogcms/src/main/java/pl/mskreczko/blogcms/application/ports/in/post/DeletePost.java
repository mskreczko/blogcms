package pl.mskreczko.blogcms.application.ports.in.post;

import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;

import java.util.UUID;

public interface DeletePost {
    void deletePost(UUID postId) throws NoSuchEntityException;
}
