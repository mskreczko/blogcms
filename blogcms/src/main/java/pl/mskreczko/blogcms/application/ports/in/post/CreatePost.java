package pl.mskreczko.blogcms.application.ports.in.post;

import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.NewPostDto;

public interface CreatePost {
    void createPost(NewPostDto newPostDto) throws NoSuchEntityException;
}
