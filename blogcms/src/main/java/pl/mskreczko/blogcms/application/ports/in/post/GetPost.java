package pl.mskreczko.blogcms.application.ports.in.post;

import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.PostDto;

import java.util.UUID;

public interface GetPost {
    PostDto getPost(UUID postId) throws NoSuchEntityException;
}
